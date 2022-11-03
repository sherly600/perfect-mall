package com.pmall.user.controller;

import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.commons.tool.utils.CookieUtil;
import com.pmall.user.IKaptchaService;
import com.pmall.user.annotation.Anoymous;
import com.pmall.user.constants.SysRetCodeConstants;
import com.pmall.user.dto.KaptchaCodeRequest;
import com.pmall.user.dto.KaptchaCodeResponse;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class CaptchaController {

    @Reference(timeout = 3000)
    IKaptchaService kaptchaService;

    /**
     *
     */
    @Anoymous
    @GetMapping("/kaptcha")
    public ResponseData getKaptchaCode(HttpServletResponse response) {
        KaptchaCodeRequest kaptchaCodeRequest=new KaptchaCodeRequest();
        KaptchaCodeResponse kaptchaCodeResponse=kaptchaService.getKaptchaCode(kaptchaCodeRequest);
        if(kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())){
            Cookie cookie=CookieUtil.genCookie("kaptcha_uuid",kaptchaCodeResponse.getUuid(),"/",60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseUtil<>().setData(kaptchaCodeResponse.getImageCode());
        }
        return new ResponseUtil<>().setErrorMsg(kaptchaCodeResponse.getCode());
    }

    @Anoymous
    @PostMapping("/kaptcha")
    public ResponseData validKaptchaCode(@RequestBody String code,HttpServletRequest httpServletRequest) {
        KaptchaCodeRequest request = new KaptchaCodeRequest();
        String uuid = CookieUtil.getCookieValue(httpServletRequest, "kaptcha_uuid");
        request.setUuid(uuid);
        request.setCode(code);
        KaptchaCodeResponse response = kaptchaService.validateKaptchaCode(request);
        if (response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil<>().setData(null);
        }
        return new ResponseUtil<>().setErrorMsg(response.getCode());
    }
}
