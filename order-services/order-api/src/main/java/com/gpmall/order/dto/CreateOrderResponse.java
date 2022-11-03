package com.pmall.order.dto;/**
 * Created by mic on 2019/7/30.
 */

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

/**
 * sherly
 * create-date: 2019/7/30-上午9:49
 */
@Data
public class CreateOrderResponse extends AbstractResponse{

    private String orderId;
}
