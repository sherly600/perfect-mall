package com.pmall.order.biz.factory;

import com.pmall.order.biz.TransOutboundInvoker;


public interface TransPipelineFactory<T> {

    TransOutboundInvoker build(T obj);
}
