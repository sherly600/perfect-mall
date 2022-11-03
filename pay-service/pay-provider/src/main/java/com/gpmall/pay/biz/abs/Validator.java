package com.pmall.pay.biz.abs;


import com.pmall.commons.result.AbstractRequest;

/**
 * 数据验证接口类
 * @author
 */
public interface Validator {
    /**
     * 数据验证
     * @param request
     */
    void validate(AbstractRequest request);
}
