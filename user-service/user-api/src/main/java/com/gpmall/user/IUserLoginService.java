package com.pmall.user;

import com.pmall.user.dto.CheckAuthRequest;
import com.pmall.user.dto.CheckAuthResponse;
import com.pmall.user.dto.UserLoginRequest;
import com.pmall.user.dto.UserLoginResponse;

public interface IUserLoginService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}
