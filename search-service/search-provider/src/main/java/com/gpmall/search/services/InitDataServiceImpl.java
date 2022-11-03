package com.pmall.search.services;

import com.pmall.search.InitDataService;
import com.pmall.search.converter.ProductConverter;
import com.pmall.search.dal.entitys.Item;
import com.pmall.search.dal.persistence.ItemMapper;
import com.pmall.search.entity.ItemDocument;
import com.pmall.search.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
@Service
public class InitDataServiceImpl implements InitDataService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ProductConverter productConverter;

    @Override
    public void initItems() {
        List<Item> items=itemMapper.selectAll();
        items.parallelStream().forEach(item->{
            productRepository.save(productConverter.item2Document(item));
        });
    }
}
