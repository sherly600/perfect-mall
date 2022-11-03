package com.pmall.order.biz.handler;

import com.pmall.order.biz.TransOutboundInvoker;
import com.pmall.order.biz.handler.TransHandler;


public interface TransPipeline extends TransOutboundInvoker {

    /**
     *
     * @param handlers
     */
    void addFirst(TransHandler... handlers);

    /**
     *
     * @param handlers
     */
    void addLast(TransHandler ... handlers);
}
