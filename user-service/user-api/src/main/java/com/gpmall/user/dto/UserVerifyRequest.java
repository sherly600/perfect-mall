package com.pmall.user.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.user.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class UserVerifyRequest extends AbstractRequest {

    private String userName;
    /**
     * 注册时生产的唯一序号
     */
    private String uuid;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(uuid)){
            throw new ValidateException(SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
