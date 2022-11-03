package com.pmall.search.utils;

import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ExceptionUtil;
import com.pmall.search.consts.SearchRetCode;


public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(SearchRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(SearchRetCode.SYSTEM_ERROR.getMsg());
        }
    }
}
