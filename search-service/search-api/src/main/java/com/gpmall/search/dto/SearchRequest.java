package com.pmall.search.dto;

import com.pmall.commons.result.AbstractRequest;
import com.pmall.commons.tool.exception.ValidateException;
import com.pmall.search.SearchException;
import com.pmall.search.consts.SearchRetCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 通用请求类
 *
 * @author sherly
 * @version v1.0.0
 * @Date 2019年8月10日
 */
@Data
public class SearchRequest extends AbstractRequest {

    private String keyword;
    private Integer currentPage;
    private Integer pageSize;
    private String sort;
    private Integer priceGt;
    private Integer priceLte;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(keyword)){
            throw new ValidateException(
                    SearchRetCode.REQUEST_CHECK_FAILURE.getCode(),
                    SearchRetCode.REQUEST_CHECK_FAILURE.getMsg());
        }
    }


    @Override
    public String toString() {
        return "SearchRequest{" +
                "keyword='" + keyword + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
