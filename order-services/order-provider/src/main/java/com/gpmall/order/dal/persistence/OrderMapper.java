package com.pmall.order.dal.persistence;

import com.pmall.commons.tool.tkmapper.TkMapper;
import com.pmall.order.dal.entitys.Order;

public interface OrderMapper extends TkMapper<Order> {
    Long countAll();
}