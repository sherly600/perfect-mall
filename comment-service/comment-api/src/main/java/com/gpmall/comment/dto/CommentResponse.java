package com.pmall.comment.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * @author sherly
 * @date 2019/8/14 23:24
 * 根据订单详情id查看评价返回结果
 */
@Data
public class CommentResponse extends AbstractResponse {

    private List<CommentDto> commentDtoList;
}
