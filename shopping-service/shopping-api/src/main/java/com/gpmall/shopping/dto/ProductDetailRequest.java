package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.shopping.constants.ShoppingRetCode;
import lombok.Data;


@Data
public class ProductDetailRequest extends AbstractRequest {

    private Long id;

    @Override
    public void requestCheck() {
        if(id==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
