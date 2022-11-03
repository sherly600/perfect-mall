package com.pmall.shopping.dto;

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class HomePageResponse extends AbstractResponse {

    private Set<PanelDto> panelContentItemDtos;
}
