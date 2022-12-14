package com.pmall.shopping.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * sherly
 * create-date: 2019/7/29-下午10:23
 */
@Data
@ApiModel
public class PageInfo {

    @ApiModelProperty(name = "page", value = "页码", example = "1")
    private Integer page;

    @ApiModelProperty(name = "size", value = "每页条数", example = "10")
    private Integer size;

    @ApiModelProperty(name = "sort", value = "是否排序")
    private String sort;

    @ApiModelProperty(name = "priceGt", value = "价格最小值", example = "1")
    private Integer priceGt;

    @ApiModelProperty(name = "priceLte", value = "价格最大值", example = "10")
    private Integer priceLte;

    @ApiModelProperty(name = "cid", value = "页码", example = "1")
    private Long cid;
}
