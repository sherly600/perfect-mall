package com.pmall.pay.biz.abs;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.BizException;
import com.perfectu.pay.dto.PaymentNotifyRequest;

/**
 * @author shelry
 */
public interface Payment {

    /**
     * 发起交易执行的过程
     * @param request
     * @return
     * @throws BizException
     */
    <T extends AbstractResponse> T process(AbstractRequest request) throws BizException;

    /**
     * 完成交易结果的处理
     * @param request
     * @return
     * @throws BizException
     */
    <T extends AbstractResponse> T completePayment(PaymentNotifyRequest request) throws BizException;
}
