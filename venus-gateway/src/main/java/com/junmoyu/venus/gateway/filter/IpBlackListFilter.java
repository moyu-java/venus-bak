package com.junmoyu.venus.gateway.filter;

import com.junmoyu.venus.gateway.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * IP 黑名单过滤器
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@Slf4j
@Component
public class IpBlackListFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 具体实现可以通过 Redis 的布隆过滤器判断远程 host 所处 IP 是否存在黑名单中
        String host = exchange.getRequest().getURI().getHost();

        log.info("远程访问地址：{}", host);
        if ("127.0.0.1".equals(host)) {
            return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "请求地址不允许访问");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
