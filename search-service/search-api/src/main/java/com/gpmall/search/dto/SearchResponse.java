package com.pmall.search.dto;


import com.pmall.commons.result.AbstractResponse;
import com.pmall.search.consts.SearchRetCode;
import lombok.Data;

import java.util.List;

/**
 * 通用响应类
 *
 * @author sherly
 * @version v1.0.0
 * @Date 2019年8月10日
 */
@Data
public class SearchResponse<T> extends AbstractResponse {
     private Long total;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public SearchResponse setData(List<T> data) {
        this.data = data;
        return this;
    }

    public SearchResponse ok(List<T> data) {
        this.setCode(SearchRetCode.SUCCESS.getCode());
        this.setMsg(SearchRetCode.SUCCESS.getMsg());
        this.setTotal(total);
        this.setData(data);
        return this;
    }
}
