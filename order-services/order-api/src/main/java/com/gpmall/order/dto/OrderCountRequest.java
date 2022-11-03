package com.pmall.order.dto;/**
 * Created by mic on 2019/7/30.
 */

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.order.constant.OrderRetCode;
import lombok.Data;


@Data
public class OrderCountRequest extends AbstractRequest{

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
