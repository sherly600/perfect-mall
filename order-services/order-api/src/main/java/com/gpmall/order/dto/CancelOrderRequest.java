package com.pmall.order.dto;/**
 * Created by mic on 2019/7/30.
 */

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.order.constant.OrderRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * sherly
 * create-date: 2019/7/30-上午9:55
 */
@Data
public class CancelOrderRequest extends AbstractRequest{

    private String orderId;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(orderId)){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
