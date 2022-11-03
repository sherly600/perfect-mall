package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;


@Data
public class NavListResponse extends AbstractResponse {

    private List<PanelContentDto> pannelContentDtos;
}
