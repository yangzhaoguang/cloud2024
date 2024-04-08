package com.atguigu.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Author: YZG
 * Date: 2024/4/7 18:08
 * Description:
 */
@RestController
public class EmpowerController //Empower授权规则，用来处理请求的来源
{
    @GetMapping(value = "/empower")
    public String requestSentinel4(){
        return "Sentinel授权规则";
    }
}
