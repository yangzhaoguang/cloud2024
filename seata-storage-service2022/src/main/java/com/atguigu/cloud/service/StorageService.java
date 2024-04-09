package com.atguigu.cloud.service;

/**
 *
 * Author: YZG
 * Date: 2024/4/8 22:24
 * Description:
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}

