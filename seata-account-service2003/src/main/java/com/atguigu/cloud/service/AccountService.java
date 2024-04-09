package com.atguigu.cloud.service;

import org.apache.ibatis.annotations.Param;

/**
 *
 * Author: YZG
 * Date: 2024/4/8 22:34
 * Description:
 */
public interface AccountService {
    /**
     * 扣减库存
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);

}
