package com.pmall.user;

import com.pmall.user.dto.UserVerifyRequest;
import com.pmall.user.dto.UserVerifyResponse;

public interface IUserVerifyService {



    /**
     * 激活邮件
     * @param userVerifyRequest
     * @return
     */
    UserVerifyResponse verifyMemer(UserVerifyRequest userVerifyRequest);
}
