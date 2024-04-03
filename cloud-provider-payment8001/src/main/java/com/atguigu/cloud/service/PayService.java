package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Author: YZG
 * Date: 2024/4/3 18:14
 * Description:
 */
@Service
public interface PayService {
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay   getById(Integer id);
    public List<Pay> getAll();
}

