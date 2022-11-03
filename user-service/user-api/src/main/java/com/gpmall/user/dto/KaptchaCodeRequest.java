package com.pmall.user.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.user.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class KaptchaCodeRequest extends AbstractRequest {

    private String uuid;

    private String code;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(uuid)||StringUtils.isBlank(code)){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
