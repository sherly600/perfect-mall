package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractRequest;
import lombok.Data;


@Data
public class AddCartRequest extends AbstractRequest{

    private Long userId;
    private Long itemId;
    private Integer num;

    @Override
    public void requestCheck() {

    }
}
