package com.pmall.comment.dto;

import com.pmall.comment.constant.CommentRetCode;
import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sherly
 * @date 2019/8/21 22:17
 * 新增评价回复请求参数
 */
@Data
public class AddCommentReplyRequest extends AbstractRequest {

    /**
     * 商品评价id或者回复意见id
     */
    private String commentId;

    /**
     * 回复用户id
     */
    private Long userId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复人昵称
     */
    private String replyNick;
    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(commentId) || StringUtils.isEmpty(content) || null == userId) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
