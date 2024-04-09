package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

/**
 *
 * Author: YZG
 * Date: 2024/4/8 22:04
 * Description:
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}


