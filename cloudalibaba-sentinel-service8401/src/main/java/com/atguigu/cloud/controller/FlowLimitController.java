package com.atguigu.cloud.controller;

import com.atguigu.cloud.controller.service.FlowLimitService;
import com.atguigu.cloud.feignService.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.beans.IntrospectionException;

/**
 *
 * Author: YZG
 * Date: 2024/4/7 15:47
 * Description:
 */
@RestController
public class FlowLimitController {

    // @Resource
    // private PayFeignApi payFeignApi;

    @GetMapping("/testA")
    public String testA() {
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println("调用了TestB");
        return "------testB";
    }

    /**流控-链路演示demo
     * C和D两个请求都访问flowLimitService.common()方法，阈值到达后对C限流，对D不管
     */
    @Resource private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC()
    {
        flowLimitService.common();
        return "------testC";
    }
    @GetMapping("/testD")
    public String testD()
    {
        flowLimitService.common();
        return "------testD";
    }
}
