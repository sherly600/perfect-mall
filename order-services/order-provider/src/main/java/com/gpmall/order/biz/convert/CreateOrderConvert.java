package com.pmall.order.biz.convert;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.order.biz.context.CreateOrderContext;
import com.pmall.order.biz.context.TransHandlerContext;
import com.pmall.order.constant.OrderRetCode;
import com.pmall.order.dto.CreateOrderRequest;
import com.pmall.order.dto.CreateOrderResponse;


public class CreateOrderConvert implements TransConvert{

    @Override
    public TransHandlerContext convertRequest2Ctx(AbstractRequest req, TransHandlerContext context) {
        CreateOrderRequest createOrderRequest=(CreateOrderRequest)req;
        CreateOrderContext createOrderContext=(CreateOrderContext) context;
        createOrderContext.setAddressId(createOrderRequest.getAddressId());
        createOrderContext.setCartProductDtoList(createOrderRequest.getCartProductDtoList());
        createOrderContext.setOrderTotal(createOrderRequest.getOrderTotal());
        createOrderContext.setStreetName(createOrderRequest.getStreetName());
        createOrderContext.setTel(createOrderRequest.getTel());
        createOrderContext.setUserId(createOrderRequest.getUserId());
        createOrderContext.setUserName(createOrderRequest.getUserName());
        createOrderContext.setUniqueKey(createOrderRequest.getUniqueKey());
        return createOrderContext;
    }

    @Override
    public AbstractResponse convertCtx2Respond(TransHandlerContext ctx) {
        CreateOrderContext createOrderContext=(CreateOrderContext) ctx;
        CreateOrderResponse createOrderResponse=new CreateOrderResponse();
        createOrderResponse.setOrderId(createOrderContext.getOrderId());
        createOrderResponse.setCode(OrderRetCode.SUCCESS.getCode());
        createOrderResponse.setMsg(OrderRetCode.SUCCESS.getMessage());
        return createOrderResponse;
    }
}
