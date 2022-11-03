package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.shopping.constants.ShoppingRetCode;
import lombok.Data;

import java.util.List;


@Data
public class ClearCartItemRequest extends AbstractRequest{

    private Long userId;
    private List<Long> productIds;
    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
