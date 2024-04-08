package com.atguigu.cloud.feignService;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 *
 * Author: YZG
 * Date: 2024/4/7 22:41
 * Description:
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
