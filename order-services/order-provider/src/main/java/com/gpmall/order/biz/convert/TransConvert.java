
package com.pmall.order.biz.convert;


import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.order.biz.context.TransHandlerContext;


public interface TransConvert {
    /**
     * 请求转上下文
     * 
     * @param req
     * @return
     */
    TransHandlerContext convertRequest2Ctx(AbstractRequest req, TransHandlerContext context);
    
    /**
     * 上下文转应答
     * 
     * @param ctx
     * @return
     */
    AbstractResponse convertCtx2Respond(TransHandlerContext ctx);
}
