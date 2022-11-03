package com.pmall.order.biz.handler;

import com.pmall.commons.tool.exception.BizException;
import com.pmall.order.biz.callback.TransCallback;
import com.pmall.order.biz.context.CreateOrderContext;
import com.pmall.order.biz.context.TransHandlerContext;
import com.pmall.order.constant.OrderRetCode;
import com.pmall.shopping.ICartService;
import com.pmall.shopping.dto.ClearCartItemRequest;
import com.pmall.shopping.dto.ClearCartItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 *
 * 将购物车中的缓存失效
 */
@Slf4j
@Component
public class ClearCartItemHandler extends AbstractTransHandler {
    @Reference(check = false,mock = "com.pmall.order.biz.mock.MockCartService")
    ICartService cartService;

    //是否采用异步方式执行
    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        log.info("begin - ClearCartItemHandler-context:"+context);
        //TODO 缓存失效和下单是属于两个事物操作，需要保证成功，后续可以改造成消息队列的形式来实现
        ClearCartItemRequest request=new ClearCartItemRequest();
        CreateOrderContext createOrderContext=(CreateOrderContext)context;
        request.setProductIds(createOrderContext.getBuyProductIds());
        request.setUserId(createOrderContext.getUserId());
        ClearCartItemResponse response=cartService.clearCartItemByUserID(request);
        if(OrderRetCode.SUCCESS.getCode().equals(response.getCode())){
            return true;
        }else{
            throw new BizException(response.getCode(),response.getMsg());
        }
    }
}
