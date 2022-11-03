package com.pmall.shopping.services;

import com.pmall.shopping.IContentService;
import com.pmall.shopping.constant.GlobalConstants;
import com.pmall.shopping.constants.ShoppingRetCode;
import com.pmall.shopping.converter.ContentConverter;
import com.pmall.shopping.dal.entitys.PanelContent;
import com.pmall.shopping.dal.persistence.PanelContentMapper;
import com.pmall.shopping.dto.NavListResponse;
import com.pmall.shopping.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Slf4j
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
    public NavListResponse queryNavList() {
        NavListResponse response=new NavListResponse();
        try {
            Example exampleContent = new Example(PanelContent.class);
            exampleContent.setOrderByClause("sort_order");
            Example.Criteria criteriaContent = exampleContent.createCriteria();
            criteriaContent.andEqualTo("panelId", GlobalConstants.HEADER_PANEL_ID);
            List<PanelContent> pannelContents = panelContentMapper.selectByExample(exampleContent);
            //添加缓存操作 TODO
            response.setPannelContentDtos(contentConverter.panelContents2Dto(pannelContents));
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("ContentServiceImpl.queryNavList Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
