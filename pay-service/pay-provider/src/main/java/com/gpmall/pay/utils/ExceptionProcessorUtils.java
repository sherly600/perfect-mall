package com.pmall.pay.utils;

import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ExceptionUtil;
import com.perfectu.pay.constants.PayReturnCodeEnum;

/**
 *
 * @author
 * create-date: 2019/7/22-15:48
 */
public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response,Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(PayReturnCodeEnum.SYSTEM_ERROR.getCode());
            response.setMsg(PayReturnCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
