package com.pmall.pay.biz.payment.validator;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.BizException;
import com.pmall.order.OrderQueryService;
import com.pmall.order.dto.OrderDetailRequest;
import com.pmall.order.dto.OrderDetailResponse;
import com.pmall.pay.biz.abs.BaseValidator;
import com.perfectu.pay.constants.PayReturnCodeEnum;
import com.perfectu.pay.dto.PaymentRequest;
import com.perfectu.pay.dto.RefundRequest;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 * @author
 */
@Service("wechatPaymentValidator")
public class WechatPaymentValidator extends BaseValidator {
     @Reference(timeout=3000)
     OrderQueryService orderQueryService;
    @Override
    public void specialValidate(AbstractRequest request) {
        commonValidate(request,orderQueryService);
    }
}
