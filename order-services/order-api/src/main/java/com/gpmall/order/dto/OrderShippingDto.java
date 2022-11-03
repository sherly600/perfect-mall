package com.pmall.order.dto;/**
 * Created by mic on 2019/7/31.
 */

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderShippingDto implements Serializable{

    private String orderId;

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;
}
