package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.shopping.constants.ShoppingRetCode;
import lombok.Data;


@Data
public class AllProductRequest extends AbstractRequest {

    private Integer page;
    private Integer size;
    private String sort;
    private Long cid;
    private Integer priceGt;
    private Integer priceLte;

    @Override
    public void requestCheck() {
        if(page<=0){
            setPage(1);
        }
    }
}
