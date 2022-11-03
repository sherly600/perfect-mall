package com.pmall.order.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.order.constant.OrderRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class OrderItemRequest extends AbstractRequest {

    private String orderItemId;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(orderItemId)) {
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
