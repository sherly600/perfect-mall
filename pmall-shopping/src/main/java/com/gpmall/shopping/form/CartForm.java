package com.pmall.shopping.form;/**
 * Created by mic on 2019/7/26.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * sherly
 * create-date: 2019/7/26-下午4:50
 */
@Data
@ApiModel
public class CartForm {

    @ApiModelProperty(name = "userId", value = "用户ID", example = "10000")
    private Long userId;

    @ApiModelProperty(name = "productId", value = "产品ID", example = "10000")
    private Long productId;

    @ApiModelProperty(name = "checked", value = "是否选中")
    private String checked;

    @ApiModelProperty(name = "productNum", value = "商品数量", example = "1")
    private Integer productNum;


}
