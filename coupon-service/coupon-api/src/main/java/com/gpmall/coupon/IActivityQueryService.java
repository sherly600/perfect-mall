package com.pmall.coupon;

import java.util.List;

/**
 * 营销活动Service
 * 此接口暂为草稿
 */
public interface IActivityQueryService {
    // 查询所有活动
    List queryAll();
    // 查询正在进行中的活动
    List queryActiveActivityList();
    // 检查创建定时传递的活动id是否有效
    void checkActivityIsValid(Integer id);

    // 统计查询
}
