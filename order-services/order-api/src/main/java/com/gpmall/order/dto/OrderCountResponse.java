package com.pmall.order.dto;/**
 * Created by mic on 2019/7/30.
 */

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;


@Data
public class OrderCountResponse extends AbstractResponse{

    private int count;
}
