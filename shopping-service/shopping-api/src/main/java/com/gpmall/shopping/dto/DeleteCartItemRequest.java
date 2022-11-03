package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.shopping.constants.ShoppingRetCode;
import lombok.Data;

@Data
public class DeleteCartItemRequest extends AbstractRequest{
    private Long userId;
    private Long itemId;

    @Override
    public void requestCheck() {
        if(userId==null||itemId==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
