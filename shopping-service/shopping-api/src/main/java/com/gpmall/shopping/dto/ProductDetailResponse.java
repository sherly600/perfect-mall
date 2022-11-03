package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;



@Data
public class ProductDetailResponse extends AbstractResponse {
    private ProductDetailDto productDetailDto;
}
