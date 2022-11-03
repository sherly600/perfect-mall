package com.pmall.pay.biz.payment.context;


import com.pmall.pay.biz.abs.PaymentContext;

import java.util.SortedMap;

/**
 * sherly
 * 支付宝支付的上下文信息
 */
public class AliPaymentContext extends PaymentContext {
    /** 商品名称（必填）*/
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
