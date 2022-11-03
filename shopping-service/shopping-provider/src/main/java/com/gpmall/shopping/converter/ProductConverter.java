package com.pmall.shopping.converter;

import com.pmall.shopping.dal.entitys.Item;
import com.pmall.shopping.dto.ProductDetailDto;
import com.pmall.shopping.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductConverter {

    @Mappings({
            @Mapping(source = "id",target = "productId"),
            @Mapping(source = "title",target = "productName"),
            @Mapping(source = "price",target = "salePrice"),
            @Mapping(source = "sellPoint",target = "subTitle"),
            @Mapping(source = "imageBig",target = "picUrl")
    })
    ProductDto item2Dto(Item item);

    List<ProductDto> items2Dto(List<Item> items);
}
