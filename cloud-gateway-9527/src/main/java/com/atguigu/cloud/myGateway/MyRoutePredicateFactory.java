package com.atguigu.cloud.myGateway;

import io.netty.util.internal.StringUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.AfterRoutePredicateFactory.Config;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 自动以断言工厂
 * Author: YZG
 * Date: 2024/4/6 11:50
 * Description:
 * 需求: 希望用一个 userType 字段来描述用户的会员等级(Diamonds, gold, silver)。
 * 在配置文件中可以配置哪种等级的会员可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    // 1、空参构造器，调用父类的构造器
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }


    //  2、配置类
    @Validated
    public static class Config {
        @Getter@Setter@NotEmpty
        private String userType;
    }

    // 3、具体的校验规则
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //  获取请求参数中的第一个参数
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                //  若为null返回false
                if (StringUtil.isNullOrEmpty(userType)) return false;
                // 请求参数中的userTYpe与配置的userType相等，则返回true
                if (userType.equalsIgnoreCase(config.getUserType())) return true;
                return false;
            }
        };
    }


    //  4、快捷方式
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }


}
