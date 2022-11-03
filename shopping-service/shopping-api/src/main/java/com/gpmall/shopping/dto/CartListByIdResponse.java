package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;


@Data
public class CartListByIdResponse extends AbstractResponse {

    private List<CartProductDto> cartProductDtos;
}
