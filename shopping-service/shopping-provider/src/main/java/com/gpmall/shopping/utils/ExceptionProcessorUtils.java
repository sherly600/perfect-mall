package com.pmall.shopping.utils;

import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ExceptionUtil;
import com.pmall.shopping.constants.ShoppingRetCode;


public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(ShoppingRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(ShoppingRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
