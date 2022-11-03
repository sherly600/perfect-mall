package com.pmall.shopping.controller;

import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.shopping.IContentService;
import com.pmall.shopping.IHomeService;
import com.pmall.shopping.constants.ShoppingRetCode;
import com.pmall.shopping.dto.HomePageResponse;
import com.pmall.shopping.dto.NavListResponse;
import com.pmall.user.annotation.Anoymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sherly
 * create-date: 2019/7/23-17:06
 */
@RestController
@RequestMapping("/shopping")
@Api(tags = "HomeController", description = "导航控制层")
public class HomeController {

    @Reference(timeout = 3000)
    IContentService contentService;

    @Reference(timeout = 3000)
    IHomeService iHomeService;

    @Anoymous
    @GetMapping("/navigation")
    @ApiOperation("导航")
    public ResponseData navigation(){
        NavListResponse response=contentService.queryNavList();
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getPannelContentDtos());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

    @Anoymous
    @GetMapping("/homepage")
    @ApiOperation("主页")
    public ResponseData homepage(){
        HomePageResponse response=iHomeService.homepage();
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getPanelContentItemDtos());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }


}
