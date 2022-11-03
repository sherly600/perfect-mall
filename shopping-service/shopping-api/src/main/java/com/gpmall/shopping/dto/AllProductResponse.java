package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;


@Data
public class AllProductResponse extends AbstractResponse {

    private List<ProductDto> productDtoList;

    private Long total;
}
