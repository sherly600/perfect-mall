package com.pmall.shopping.converter;

import com.pmall.shopping.dal.entitys.Panel;
import com.pmall.shopping.dal.entitys.PanelContent;
import com.pmall.shopping.dal.entitys.PanelContentItem;
import com.pmall.shopping.dto.PanelContentDto;
import com.pmall.shopping.dto.PanelContentItemDto;
import com.pmall.shopping.dto.PanelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ContentConverter {

    @Mappings({})
    PanelContentDto panelContent2Dto(PanelContent panelContent);

    @Mappings({})
    PanelContentDto panelContentItem2Dto(PanelContentItem panelContentItem);

    @Mappings({})
    PanelDto panen2Dto(Panel panel);

    List<PanelContentDto> panelContents2Dto(List<PanelContent> panelContents);

    List<PanelContentItemDto> panelContentItem2Dto(List<PanelContentItem> panelContentItems);

}
