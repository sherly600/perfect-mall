package com.pmall.cashier.bootstrap.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pmall.cashier.bootstrap.form.PayForm;
import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.user.annotation.Anoymous;
import com.pmall.user.intercepter.TokenIntercepter;
import com.perfectu.pay.PayCoreService;
import com.perfectu.pay.constants.PayReturnCodeEnum;
import com.perfectu.pay.dto.PaymentRequest;
import com.perfectu.pay.dto.PaymentResponse;
import com.perfectu.pay.dto.RefundRequest;
import com.perfectu.pay.dto.RefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@Slf4j
@RestController
@RequestMapping("/cashier")
public class PayController {

    @Reference(timeout = 3000,retries = 0)
    PayCoreService payCoreService;

    @PostMapping("/pay")
    @Anoymous
    public ResponseData pay(@RequestBody PayForm payForm, HttpServletRequest httpServletRequest){
        log.info("支付表单数据:{}",payForm);
        PaymentRequest request=new PaymentRequest();
        String userInfo=(String)httpServletRequest.getAttribute(TokenIntercepter.USER_INFO_KEY);
        JSONObject object= JSON.parseObject(userInfo);
        Long uid=Long.parseLong(object.get("uid").toString());
        request.setUserId(uid);
        BigDecimal money=payForm.getMoney();
        request.setOrderFee(money);
        request.setPayChannel(payForm.getPayType());
        request.setSubject(payForm.getInfo());
        request.setSpbillCreateIp("115.195.125.164");
        request.setTradeNo(payForm.getOrderId());
        request.setTotalFee(money);
        PaymentResponse response=payCoreService.execPay(request);
        if(response.getCode().equals(PayReturnCodeEnum.SUCCESS.getCode())){
            return new ResponseUtil<>().setData(response.getHtmlStr());
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());

    }


    @PostMapping("/refund")
    @Anoymous
    public ResponseData refund(@RequestBody PayForm refundForm,HttpServletRequest httpServletRequest){
        log.info("订单退款入参:{}",JSON.toJSONString(refundForm));
        RefundRequest refundRequest=new RefundRequest();
        String userInfo=(String)httpServletRequest.getAttribute(TokenIntercepter.USER_INFO_KEY);
        JSONObject object= JSON.parseObject(userInfo);
        Long uid=Long.parseLong(object.get("uid").toString());
        refundRequest.setUserId(uid);
        refundRequest.setOrderId(refundForm.getOrderId());
        refundRequest.setRefundAmount(refundForm.getMoney());
        refundRequest.setPayChannel(refundForm.getPayType());
        RefundResponse refundResponse=payCoreService.execRefund(refundRequest);
        log.info("订单退款同步返回结果:{}",JSON.toJSONString(refundResponse));
        return new ResponseUtil<>().setData(refundResponse);
    }


}
