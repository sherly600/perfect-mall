package com.pmall.order.dal.persistence;

import com.pmall.commons.tool.tkmapper.TkMapper;
import com.pmall.order.dal.entitys.Stock;

import java.util.List;

/**
 * @Description:
 * @Author： wz
 * @Date: 2019-09-16 00:09
 **/
public interface StockMapper extends TkMapper<Stock> {
 void updateStock(Stock stock);
 Stock selectStockForUpdate(Long itemId);
 Stock selectStock(Long itemId);
 List<Stock> findStocksForUpdate(List<Long> itemIds);
}