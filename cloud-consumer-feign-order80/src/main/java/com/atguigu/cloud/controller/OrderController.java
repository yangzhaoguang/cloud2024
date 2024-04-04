package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.feignService.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Author: YZG
 * Date: 2024/4/3 19:06
 * Description:
 */
@RestController
public class OrderController{

    @Autowired
    private PayFeignApi payFeignApi;
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO)
    {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id)
    {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("开始调用getPayInfo，时间: "  + DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            // throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println("结束调用getPayInfo，时间: "  + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),ReturnCodeEnum.RC500.getMessage());
        }
        return resultData;
    }

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb()
    {
        return payFeignApi.mylb();
    }
}
