package com.pmall.user.controller;

import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.commons.tool.utils.CookieUtil;
import com.pmall.user.IKaptchaService;
import com.pmall.user.IUserRegisterService;
import com.pmall.user.IUserVerifyService;
import com.pmall.user.annotation.Anoymous;
import com.pmall.user.constants.SysRetCodeConstants;
import com.pmall.user.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户注册激活
 */
@RestController
@RequestMapping("/user")
public class UserVerifyController {

    @Reference(timeout = 3000)
    IUserRegisterService iUserRegisterService;

    @Reference(timeout = 3000)
    IUserVerifyService iUserVerifyService;

    @Anoymous
    @GetMapping("/verify")
    public ResponseData register(@RequestParam String uuid,@RequestParam String username, HttpServletRequest request){
        if(!(StringUtils.isNotBlank(uuid) &&  StringUtils.isNotBlank(username))){
            return new ResponseUtil<>().setErrorMsg("注册序号/用户名不允许为空");
        }
        UserVerifyRequest userVerifyRequest = new UserVerifyRequest();
        userVerifyRequest.setUserName(username);
        userVerifyRequest.setUuid(uuid);
        UserVerifyResponse userVerifyResponse = iUserVerifyService.verifyMemer(userVerifyRequest);
        if(userVerifyResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil().setData(null);
        }else{
            return new ResponseUtil().setData(userVerifyResponse.getMsg());
        }
    }
}
