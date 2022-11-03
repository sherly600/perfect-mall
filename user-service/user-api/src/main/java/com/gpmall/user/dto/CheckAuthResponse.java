package com.pmall.user.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;


@Data
public class CheckAuthResponse extends AbstractResponse {

    private String userinfo;
}
