package com.junmoyu.venus.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * 局部过滤器 - 请求时间统计
 * 需要设置 spring.cloud.gateway.discovery.locator.enabled=false 局部过滤器才可生效
 * 配置方式见 /script/nacos/venus-gateway.yaml
 *
 * @author moyu.jun
 * @date 2022/3/25
 */
@Slf4j
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    public RequestTimeGatewayFilterFactory() {
        super(RequestTimeGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("show");
    }

    @Override
    public GatewayFilter apply(RequestTimeGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            if (!config.isShow()) {
                return chain.filter(exchange);
            }
            // 请求前置操作
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 请求后置操作
                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                if (startTime != null) {
                    log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                }
            }));
        };
    }

    @Getter
    @Setter
    public static class Config {
        private boolean show;
    }
}
