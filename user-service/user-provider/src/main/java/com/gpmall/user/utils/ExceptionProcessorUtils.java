package com.pmall.user.utils;

import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ExceptionUtil;
import com.pmall.user.constants.SysRetCodeConstants;

/**
 * sherly
 * create-date: 2019/7/22-15:48
 */
public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(SysRetCodeConstants.SYSTEM_ERROR.getCode());
            response.setMsg(SysRetCodeConstants.SYSTEM_ERROR.getMessage());
        }
    }
}
