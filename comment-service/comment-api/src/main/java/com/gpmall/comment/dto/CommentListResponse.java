package com.pmall.comment.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * @author sherly
 * @date 2019/8/15 23:09
 * 分页查询单个商品的商品评价返回类型
 */
@Data
public class CommentListResponse extends AbstractResponse {

    private List<CommentDto> commentDtoList;

    private int page;

    private int size;

    private long total;
}
