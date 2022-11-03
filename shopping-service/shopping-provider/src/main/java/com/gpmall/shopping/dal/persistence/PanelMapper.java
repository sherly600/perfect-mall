package com.pmall.shopping.dal.persistence;

import com.pmall.commons.tool.tkmapper.TkMapper;
import com.pmall.shopping.dal.entitys.Panel;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PanelMapper extends TkMapper<Panel> {

    List<Panel> selectPanelContentById(@Param("panelId")Integer panelId);
}