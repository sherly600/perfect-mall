package com.pmall.shopping;

import com.pmall.shopping.dto.AllProductCateRequest;
import com.pmall.shopping.dto.AllProductCateResponse;


public interface IProductCateService {
    /**
     * 获取所有产品分类
     * @param request
     * @return
     */
    AllProductCateResponse getAllProductCate(AllProductCateRequest request);
}
