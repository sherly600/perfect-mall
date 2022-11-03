package com.pmall.user.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.user.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class CheckAuthRequest extends AbstractRequest {
    private String token;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(token)){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
