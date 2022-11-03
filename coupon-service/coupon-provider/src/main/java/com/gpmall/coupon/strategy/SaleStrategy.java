package com.pmall.coupon.strategy;

import com.pmall.coupon.dto.UseCouponRequest;


public abstract class SaleStrategy {
    public abstract boolean match(UseCouponRequest request);
    public abstract void perform();
}
