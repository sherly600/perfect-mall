package com.pmall.order.utils;

import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ExceptionUtil;
import com.pmall.order.constant.OrderRetCode;
import com.pmall.user.constants.SysRetCodeConstants;


public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(OrderRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(OrderRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
