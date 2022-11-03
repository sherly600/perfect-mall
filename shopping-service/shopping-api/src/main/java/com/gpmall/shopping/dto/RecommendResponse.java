package com.pmall.shopping.dto;/**
 * Created by mic on 2019/7/29.
 */

import com.pmall.commons.result.AbstractResponse;
import lombok.Data;

import java.util.Set;


@Data
public class RecommendResponse extends AbstractResponse{

    private Set<PanelDto> panelContentItemDtos;

}
