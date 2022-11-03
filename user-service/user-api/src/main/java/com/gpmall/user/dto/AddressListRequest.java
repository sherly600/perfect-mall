package com.pmall.user.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.result.AbstractResponse;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.user.constants.SysRetCodeConstants;
import lombok.Data;


@Data
public class AddressListRequest extends AbstractRequest {
    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
