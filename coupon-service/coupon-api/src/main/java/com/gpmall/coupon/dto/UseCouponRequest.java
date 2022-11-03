package com.pmall.coupon.dto;

import com.pmall.commons.result.AbstractRequest;
import lombok.Data;

import java.util.List;


@Data
public class UseCouponRequest extends AbstractRequest {
    private List<OrderItemDto> orderItemList;

    @Override
    public void requestCheck() {

    }
}
