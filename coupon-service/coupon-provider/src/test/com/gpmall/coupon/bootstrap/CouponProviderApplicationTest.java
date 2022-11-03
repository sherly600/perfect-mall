package com.pmall.coupon.bootstrap;

import com.pmall.coupon.dal.entitys.Coupon;
import com.pmall.coupon.dal.entitys.CouponCode;
import com.pmall.coupon.dal.persistence.CouponCodeMapper;
import com.pmall.coupon.dal.persistence.CouponMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponProviderApplicationTest {

    @Test
    public void contextLoads() {
    }

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponCodeMapper couponCodeMapper;
    @Test
    public void  test1(){
        Coupon coupon = couponMapper.selectByPrimaryKey(1L);
        CouponCode couponCode = couponCodeMapper.selectByPrimaryKey(1L);
        System.out.println("coupon = " + coupon);
        System.out.println("couponCode = " + couponCode);
    }

}