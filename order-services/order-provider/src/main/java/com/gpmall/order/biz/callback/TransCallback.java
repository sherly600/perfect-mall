package com.pmall.order.biz.callback;

import com.pmall.order.biz.context.TransHandlerContext;

/**
 * 交易回调
 */
public interface TransCallback {

    void onDone(TransHandlerContext context);
}
