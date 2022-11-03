package com.pmall.order.biz.handler;

import com.pmall.commons.tool.exception.BizException;
import com.pmall.order.biz.context.CreateOrderContext;
import com.pmall.order.biz.context.TransHandlerContext;
import com.pmall.order.constant.OrderRetCode;
import com.pmall.order.dal.persistence.OrderMapper;
import com.pmall.user.IMemberService;
import com.pmall.user.dto.QueryMemberRequest;
import com.pmall.user.dto.QueryMemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * TODO:  如何解决商品的超卖
 */
@Slf4j
@Component
public class ValidateHandler extends AbstractTransHandler {

    @Autowired
    OrderMapper orderMapper;

    @Reference(mock = "com.pmall.order.biz.mock.MockMemberService",check = false)
    IMemberService memberService;

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        log.info("begin ValidateHandler :context:"+context);
        CreateOrderContext createOrderContext=(CreateOrderContext)context;
        QueryMemberRequest queryMemberRequest =new QueryMemberRequest();
        queryMemberRequest.setUserId(createOrderContext.getUserId());
        QueryMemberResponse response=memberService.queryMemberById(queryMemberRequest);
        if(OrderRetCode.SUCCESS.getCode().equals(response.getCode())){
            createOrderContext.setBuyerNickName(response.getUsername());
        }else{
            throw new BizException(response.getCode(),response.getMsg());
        }
        return true;
    }
}
