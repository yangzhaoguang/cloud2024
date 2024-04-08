package com.atguigu.cloud.feignService;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * Author: YZG
 * Date: 2024/4/4 18:11
 * Description:
 */
// @FeignClient("cloud-payment-service") // 指明服务端的服务名
@FeignClient(value = "cloud-gateway") // 指明为网关
public interface PayFeignApi{
    /**
     * 新增一条支付相关流水记录
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);
    // public ResultData getPayInfo(@PathVariable Integer id); // 这样写是错误的，必须指明路径参数名称

    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    public String mylb();

    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);


    /**
     *  测试网关+ Feign
     * */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * 测试网关+ Feign
     * */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
