package com.pmall.comment.dto;

import com.pmall.comment.constant.CommentRetCode;
import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sherly
 * @date 2019/8/14 23:22
 * 根据订单详情id查看评价请求参数
 */
@Data
public class CommentRequest extends AbstractRequest {

    private String orderItemId;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(orderItemId)) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
