package com.pmall.shopping.dto;


import com.pmall.commons.result.AbstractRequest;
import lombok.Data;


@Data
public class AllProductCateRequest extends AbstractRequest {
    private String sort;

    @Override
    public void requestCheck() {

    }
}
