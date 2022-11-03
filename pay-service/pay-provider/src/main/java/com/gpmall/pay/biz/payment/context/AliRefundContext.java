package com.pmall.pay.biz.payment.context;

import com.pmall.pay.biz.abs.RefundContext;
import lombok.Data;

import java.math.BigDecimal;
import java.util.SortedMap;

/**
 * @Description:
 * @Author： sherly
 * @Date: 2019-08-16 00:32
 **/
@Data
public class AliRefundContext extends RefundContext {
	/** 商户订单号（必填）*/
	private String outTradeNo;
	/** 总金额，单位元（必填）*/
	private BigDecimal totalFee;
}