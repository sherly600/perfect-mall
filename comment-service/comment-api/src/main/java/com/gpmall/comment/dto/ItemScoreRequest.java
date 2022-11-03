package com.pmall.comment.dto;

import com.pmall.comment.constant.CommentRetCode;
import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sherly
 * @date 2019/8/18 22:18
 * 根据评价计算商品综合评分参数
 */
@Data
public class ItemScoreRequest extends AbstractRequest {

    /**
     * 商品id
     */
    private String itemId;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(itemId)) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
