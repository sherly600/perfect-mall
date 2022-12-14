package com.pmall.pay.biz.payment;

import com.alibaba.fastjson.JSON;
import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.BizException;
import com.pmall.order.OrderCoreService;
import com.pmall.pay.biz.abs.*;
import com.pmall.pay.biz.payment.channel.alipay.AlipayBuildRequest;
import com.pmall.pay.biz.payment.channel.alipay.AlipayNotify;
import com.pmall.pay.biz.payment.constants.AliPaymentConfig;
import com.pmall.pay.biz.payment.constants.PayResultEnum;
import com.pmall.pay.biz.payment.context.AliPaymentContext;
import com.pmall.pay.dal.entitys.Payment;
import com.pmall.pay.dal.persistence.PaymentMapper;
import com.perfectu.pay.constants.PayChannelEnum;
import com.perfectu.pay.constants.PayReturnCodeEnum;
import com.perfectu.pay.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author
 */
@Slf4j
@Service("aliPayment")
public class AliPayment extends BasePayment {

	@Resource(name = "aliPaymentValidator")
	private Validator validator;

	@Autowired
	AliPaymentConfig aliPaymentConfig;

	@Autowired
	PaymentMapper paymentMapper;

	@Reference(timeout = 30000)
	OrderCoreService orderCoreService;

	@Override
	public Validator getValidator() {
		return validator;
	}

	@Override
	public Context createContext(AbstractRequest request) {
		AliPaymentContext aliPaymentContext = new AliPaymentContext();
		PaymentRequest paymentRequest = (PaymentRequest) request;
		aliPaymentContext.setSubject(paymentRequest.getSubject());
		aliPaymentContext.setOutTradeNo(paymentRequest.getTradeNo());
		aliPaymentContext.setTotalFee(paymentRequest.getTotalFee());
		aliPaymentContext.setUserId(paymentRequest.getUserId());
		return aliPaymentContext;
	}

	@Override
	public void prepare(AbstractRequest request, Context context) throws BizException {
		super.prepare(request, context);
		SortedMap sParaTemp = context.getsParaTemp();
		AliPaymentContext aliPaymentContext = (AliPaymentContext) context;
		sParaTemp.put("partner", aliPaymentConfig.getAli_partner());
		sParaTemp.put("service", aliPaymentConfig.getAli_service());
		//sParaTemp.put("seller_email", aliPaymentConfig.getSeller_email());
		sParaTemp.put("seller_id", aliPaymentConfig.getSeller_id());
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("it_b_pay", aliPaymentConfig.getIt_b_pay());
		sParaTemp.put("notify_url", aliPaymentConfig.getNotify_url());
		sParaTemp.put("return_url", aliPaymentConfig.getReturn_url());
		sParaTemp.put("out_trade_no", aliPaymentContext.getOutTradeNo());
		sParaTemp.put("subject", aliPaymentContext.getSubject());
		sParaTemp.put("total_fee", aliPaymentContext.getTotalFee());
		aliPaymentContext.setsParaTemp(sParaTemp);
	}


	@Override
	public PaymentResponse generalProcess(AbstractRequest request, Context context) throws BizException {
		Map<String, Object> sPara = AlipayBuildRequest.buildRequestParam(context.getsParaTemp(), aliPaymentConfig);
		log.info("?????????????????????????????????:{}", JSON.toJSONString(sPara));
		String strPara = AlipayBuildRequest.buildRequest(sPara, "get", "??????", aliPaymentConfig);
		log.info("????????????????????????????????????:{}",strPara);
		PaymentResponse response = new PaymentResponse();
		response.setCode(PayReturnCodeEnum.SUCCESS.getCode());
		response.setMsg(PayReturnCodeEnum.SUCCESS.getMsg());
		response.setHtmlStr(strPara);
		return response;
	}


	@Override
	public void afterProcess(AbstractRequest request, AbstractResponse respond, Context context) throws BizException {
		log.info("Alipayment begin - afterProcess -request:" + request + "\n response:" + respond);
		PaymentRequest paymentRequest = (PaymentRequest) request;
		//?????????????????????
		Payment payment = new Payment();
		payment.setCreateTime(new Date());
		//?????????
		payment.setOrderId(paymentRequest.getTradeNo());
		payment.setCreateTime(new Date());
		BigDecimal amount =paymentRequest.getOrderFee();
		payment.setOrderAmount(amount);
		payment.setPayerAmount(amount);
		payment.setPayerUid(paymentRequest.getUserId());
		payment.setPayerName("");//TODO
		payment.setPayWay(paymentRequest.getPayChannel());
		payment.setProductName(paymentRequest.getSubject());
		payment.setStatus(PayResultEnum.TRADE_PROCESSING.getCode());//
		payment.setRemark("???????????????");
		payment.setUpdateTime(new Date());
		paymentMapper.insert(payment);
	}

	@Override
	public String getPayChannel() {
		return PayChannelEnum.ALI_PAY.getCode();
	}

	/**
	 * ??????????????????
	 * @param request
	 * @return
	 * @throws BizException
	 */
	@Override
	public AbstractResponse completePayment(PaymentNotifyRequest request) {
		request.requestCheck();
		Map requestParams = request.getResultMap();
		Map<String, Object> params = new HashMap<>(requestParams.size());
		requestParams.forEach((key, value) -> {
			String[] values = (String[]) value;
			params.put((String) key, StringUtils.join(values, ","));
		});

		PaymentNotifyResponse response = new PaymentNotifyResponse();
		String orderId = params.get("out_trade_no").toString();
		//??????
		if (AlipayNotify.verify(params, aliPaymentConfig)) {
			com.pmall.pay.dal.entitys.Payment payment = new Payment();
			//TRADE_FINISH(????????????)???TRADE_SUCCESS(????????????)???FAIL(????????????)
			String tradeStatus = params.get("trade_status").toString();
			if ("TRADE_SUCCESS".equals(tradeStatus)) {
				//???????????????
				payment.setPayNo(params.get("trade_no").toString());
				payment.setStatus(PayResultEnum.TRADE_SUCCESS.getCode());
				payment.setPaySuccessTime((Date) params.get("gmt_payment"));
				Example example=new Example(Payment.class);
				example.createCriteria().andEqualTo("orderId",orderId);
				paymentMapper.updateByExampleSelective(payment,example);
				//?????????????????????
				orderCoreService.updateOrder(1, orderId);
				response.setResult("success");
				return response;
			} else if ("TRADE_FINISH".equals(tradeStatus)) {
				payment.setStatus(PayResultEnum.TRADE_FINISHED.getCode());
				paymentMapper.updateByExampleSelective(payment, orderId);
				//?????????????????????
				orderCoreService.updateOrder(1, orderId);
				response.setResult("success");
			} else if ("FAIL".equals(tradeStatus)) {
				payment.setStatus(PayResultEnum.FAIL.getCode());
				paymentMapper.updateByExampleSelective(payment, orderId);
				response.setResult("success");
			} else {
				response.setResult("fail");
			}
		} else {
			throw new BizException("???????????????????????????");
		}
		return response;
	}
}
