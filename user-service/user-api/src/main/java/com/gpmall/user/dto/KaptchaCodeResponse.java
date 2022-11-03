package com.pmall.user.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;


@Data
public class KaptchaCodeResponse extends AbstractResponse {

    private String imageCode;

    private String uuid;


}
