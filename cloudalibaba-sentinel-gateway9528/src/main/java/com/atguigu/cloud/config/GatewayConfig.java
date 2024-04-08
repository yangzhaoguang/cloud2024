package com.atguigu.cloud.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Author: YZG
 * Date: 2024/4/8 17:00
 * Description:
 */
@Configuration
public class GatewayConfig {
    /**
     * 自定义限流处理器
     */
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockHandler = (serverWebExchange, throwable) -> {
            Map map = new HashMap();
            map.put("code",200);
            map.put("message","请求失败，稍后重试！");
            return ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(map));
        };
        GatewayCallbackManager.setBlockHandler(blockHandler);
    }
}
