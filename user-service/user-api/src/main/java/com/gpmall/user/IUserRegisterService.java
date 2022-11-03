package com.pmall.user;

import com.pmall.user.dto.UserRegisterRequest;
import com.pmall.user.dto.UserRegisterResponse;

public interface IUserRegisterService {

    /**
     * 实现用户注册功能
     * @param request
     * @return
     */
    UserRegisterResponse register(UserRegisterRequest request);
}
