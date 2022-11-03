package com.pmall.shopping.dal.persistence;

import com.pmall.commons.tool.tkmapper.TkMapper;
import com.pmall.shopping.dal.entitys.PanelContent;

import java.util.List;

import com.pmall.shopping.dal.entitys.PanelContentItem;
import org.apache.ibatis.annotations.Param;

public interface PanelContentMapper extends TkMapper<PanelContent> {

    List<PanelContentItem> selectPanelContentAndProductWithPanelId(@Param("panelId")Integer panelId);
}