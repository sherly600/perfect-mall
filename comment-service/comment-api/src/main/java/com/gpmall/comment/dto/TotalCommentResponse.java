package com.pmall.comment.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

/**
 * @author sherly
 * @date 2019/8/17 23:16
 */
@Data
public class TotalCommentResponse extends AbstractResponse {

    private long total;
}
