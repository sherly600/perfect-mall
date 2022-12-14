package com.pmall.pay.biz.payment;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.BizException;
import com.pmall.commons.tool.utils.UtilDate;
import com.pmall.order.OrderCoreService;
import com.pmall.pay.biz.abs.BasePayment;
import com.pmall.pay.biz.abs.Context;
import com.pmall.pay.biz.abs.Validator;
import com.pmall.pay.biz.payment.channel.wechatpay.WeChatBuildRequest;
import com.pmall.pay.biz.payment.commons.HttpClientUtil;
import com.pmall.pay.biz.payment.constants.PayResultEnum;
import com.pmall.pay.dal.entitys.Payment;
import com.pmall.pay.dal.persistence.PaymentMapper;
import com.pmall.pay.utils.GlobalIdGeneratorUtil;
import com.perfectu.pay.constants.PayChannelEnum;
import com.pmall.pay.biz.payment.constants.PaymentConstants;
import com.pmall.pay.biz.payment.constants.WechatPaymentConfig;
import com.pmall.pay.biz.payment.context.WechatPaymentContext;
import com.perfectu.pay.constants.PayReturnCodeEnum;
import com.perfectu.pay.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author
 */
@Slf4j
@Service("wechatPayment")
public class WechatPayment extends BasePayment {

	@Autowired
	private WechatPaymentConfig wechatPaymentConfig;

	@Resource(name = "wechatPaymentValidator")
	private Validator validator;

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	GlobalIdGeneratorUtil globalIdGeneratorUtil;

	private final String COMMENT_GLOBAL_ID_CACHE_KEY = "COMMENT_ID";

	@Reference(timeout = 3000)
	OrderCoreService orderCoreService;

	@Override
	public Validator getValidator() {
		return validator;
	}

	@Override
	public Context createContext(AbstractRequest request) {
		WechatPaymentContext wechatPaymentContext = new WechatPaymentContext();
		PaymentRequest paymentRequest = (PaymentRequest) request;
		wechatPaymentContext.setOutTradeNo(paymentRequest.getTradeNo());
		wechatPaymentContext.setProductId(paymentRequest.getTradeNo());
		wechatPaymentContext.setSpbillCreateIp(paymentRequest.getSpbillCreateIp());
		wechatPaymentContext.setTradeType(PaymentConstants.TradeTypeEnum.NATIVE.getType());
		wechatPaymentContext.setTotalFee(paymentRequest.getTotalFee());
		wechatPaymentContext.setBody(paymentRequest.getSubject());
		return wechatPaymentContext;
	}

	@Override
	public void prepare(AbstractRequest request, Context context) throws BizException {
		super.prepare(request, context);
		SortedMap paraMap = context.getsParaTemp();
		WechatPaymentContext wechatPaymentContext = (WechatPaymentContext) context;
		paraMap.put("body", wechatPaymentContext.getBody());
		paraMap.put("out_trade_no", wechatPaymentContext.getOutTradeNo());
		//?????????
		paraMap.put("total_fee", wechatPaymentContext.getTotalFee().multiply(new BigDecimal("100")).intValue());
		paraMap.put("spbill_create_ip", wechatPaymentContext.getSpbillCreateIp());
		paraMap.put("appid", wechatPaymentConfig.getWechatAppid());
		paraMap.put("mch_id", wechatPaymentConfig.getWechatMch_id());
		paraMap.put("nonce_str", WeChatBuildRequest.getNonceStr());
		paraMap.put("trade_type", wechatPaymentContext.getTradeType());
		paraMap.put("product_id", wechatPaymentContext.getProductId());
		// ?????????????????????????????????????????????????????????
		paraMap.put("device_info", "WEB");
		paraMap.put("notify_url", wechatPaymentConfig.getWechatNotifyurl());
		//???????????????????????????5?????????
		paraMap.put("time_expire", UtilDate.getExpireTime(30 * 60 * 1000L));
		String sign = WeChatBuildRequest.createSign(paraMap, wechatPaymentConfig.getWechatMchsecret());
		paraMap.put("sign", sign);
		log.info("????????????sign:{}", JSON.toJSONString(paraMap));
		String xml = WeChatBuildRequest.getRequestXml(paraMap);
		wechatPaymentContext.setXml(xml);
	}

	@Override
	public AbstractResponse generalProcess(AbstractRequest request, Context context) throws BizException {
		PaymentResponse response = new PaymentResponse();
		WechatPaymentContext wechatPaymentContext = (WechatPaymentContext) context;
		log.info("?????????????????????????????????:{}", wechatPaymentContext.getXml());
		String xml = HttpClientUtil.httpPost(wechatPaymentConfig.getWechatUnifiedOrder(), wechatPaymentContext.getXml());
		log.info("?????????????????????????????????:{}", xml);
		Map<String, String> resultMap = WeChatBuildRequest.doXMLParse(xml);
		if ("SUCCESS".equals(resultMap.get("return_code"))) {
			if ("SUCCESS".equals(resultMap.get("result_code"))) {
				response.setPrepayId(resultMap.get("prepay_id"));
				response.setCodeUrl(resultMap.get("code_url"));
				response.setCode(PayReturnCodeEnum.SUCCESS.getCode());
				response.setMsg(PayReturnCodeEnum.SUCCESS.getMsg());
			} else {
				String errMsg = resultMap.get("err_code") + ":" + resultMap.get("err_code_des");
				response.setCode(PayReturnCodeEnum.PAYMENT_PROCESSOR_FAILED.getCode());
				response.setMsg(PayReturnCodeEnum.PAYMENT_PROCESSOR_FAILED.getMsg(errMsg));
			}
		} else {
			response.setCode(PayReturnCodeEnum.PAYMENT_PROCESSOR_FAILED.getCode());
			response.setMsg(PayReturnCodeEnum.PAYMENT_PROCESSOR_FAILED.getMsg(resultMap.get("return_msg")));
		}
		return response;
	}

	@Override
	public void afterProcess(AbstractRequest request, AbstractResponse respond, Context context) throws BizException {
		//???????????????
		log.info("Alipayment begin - afterProcess -request:" + request + "\n response:" + respond);
		PaymentRequest paymentRequest = (PaymentRequest) request;
		//?????????????????????
		PaymentResponse response = (PaymentResponse) respond;
		Payment payment = new Payment();
		payment.setCreateTime(new Date());
		BigDecimal amount = paymentRequest.getOrderFee();
		payment.setOrderAmount(amount);
		payment.setOrderId(paymentRequest.getTradeNo());
		//payment.setTradeNo(globalIdGeneratorUtil.getNextSeq(COMMENT_GLOBAL_ID_CACHE_KEY, 1));
		payment.setPayerAmount(amount);
		payment.setPayerUid(paymentRequest.getUserId());
		payment.setPayerName("");//TODO
		payment.setPayWay(paymentRequest.getPayChannel());
		payment.setProductName(paymentRequest.getSubject());
		payment.setStatus(PayResultEnum.TRADE_PROCESSING.getCode());//
		payment.setRemark("????????????");
		payment.setPayNo(response.getPrepayId());//??????????????????id
		payment.setUpdateTime(new Date());
		paymentMapper.insert(payment);
	}

	@Override
	public String getPayChannel() {
		return PayChannelEnum.WECHAT_PAY.getCode();
	}

	@Override
	public AbstractResponse completePayment(PaymentNotifyRequest request) throws BizException {
		request.requestCheck();
		PaymentNotifyResponse response = new PaymentNotifyResponse();
		Map xmlMap = new HashMap();
		String xml = request.getXml();
		try {
			xmlMap = WXPayUtil.xmlToMap(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SortedMap<Object, Object> paraMap = new TreeMap<>();
		xmlMap.forEach(paraMap::put);
		//???????????????????????????????????????
		String rsSign = paraMap.remove("sign").toString();
		String sign = WeChatBuildRequest.createSign(paraMap, wechatPaymentConfig.getWechatMchsecret());
		//????????????
		if (rsSign.equals(sign)) {
			//SUCCESS???FAIL
			String resultCode = paraMap.get("return_code").toString();
			if ("SUCCESS".equals(resultCode)) {
				if ("SUCCESS".equals(paraMap.get("result_code"))) {
					//???????????????
					Payment payment = new Payment();
					payment.setStatus(PayResultEnum.TRADE_SUCCESS.getCode());
					payment.setPaySuccessTime((UtilDate.parseStrToDate(UtilDate.simple,paraMap.get("time_end").toString(),new Date())));
					Example example = new Example(Payment.class);
					example.createCriteria().andEqualTo("orderId", paraMap.get("out_trade_no"));
					paymentMapper.updateByExampleSelective(payment, example);
					//?????????????????????
					orderCoreService.updateOrder(1, paraMap.get("out_trade_no").toString());
					response.setResult(WeChatBuildRequest.setXML("SUCCESS", "OK"));
				}
			}
		} else {
			throw new BizException("????????????????????????????????????");
		}
		return response;
	}
}
