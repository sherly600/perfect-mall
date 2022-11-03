package com.pmall.shopping.controller;

import com.pmall.commons.result.ResponseData;
import com.pmall.commons.result.ResponseUtil;
import com.pmall.shopping.IProductCateService;
import com.pmall.shopping.constants.ShoppingRetCode;
import com.pmall.shopping.dto.AllProductCateRequest;
import com.pmall.shopping.dto.AllProductCateResponse;
import com.pmall.user.annotation.Anoymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/shopping")
@Api(tags = "ProductCateController", description = "商品种类控制层")
public class ProductCateController {
    @Reference(timeout = 3000)
    IProductCateService productCateService;

    @Anoymous
    @GetMapping("/categories")
    @ApiOperation("列举所有商品种类")
    @ApiImplicitParam(name = "sort", value = "是否排序", paramType = "query")
    public ResponseData allProductCateList(@RequestParam(value = "sort", required = false, defaultValue = "1") String sort) {
        AllProductCateRequest request = new AllProductCateRequest();
        request.setSort(sort);
        AllProductCateResponse response = productCateService.getAllProductCate(request);

        if (response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getProductCateDtoList());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
