package com.junmoyu.venus.gateway.filter;

import com.junmoyu.venus.gateway.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 局部过滤器 - URL 黑名单阻断
 * 使用 nacos 配置，可实时更新生效
 * <p>
 * 应用场景：
 * - 废弃接口
 * - 临时开放接口
 * - 需要临时关闭的接口
 * - 其他
 *
 * @author moyu.jun
 * @date 2022/3/25
 */
@Slf4j
@Component
public class BlacklistUrlGatewayFilterFactory extends AbstractGatewayFilterFactory<BlacklistUrlGatewayFilterFactory.Config> {

    public BlacklistUrlGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String url = exchange.getRequest().getURI().getPath();
            if (config.matchBlacklist(url)) {
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "请求接口不允许访问");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

        private List<String> blacklistUrl;

        private final List<Pattern> blacklistUrlPattern = new ArrayList<>();

        public boolean matchBlacklist(String url) {
            return CollectionUtils.isNotEmpty(blacklistUrlPattern) && blacklistUrlPattern.stream().anyMatch(p -> p.matcher(url).find());
        }

        public List<String> getBlacklistUrl() {
            return blacklistUrl;
        }

        public void setBlacklistUrl(List<String> blacklistUrl) {
            this.blacklistUrl = blacklistUrl;
            this.blacklistUrlPattern.clear();
            this.blacklistUrl.forEach(url -> this.blacklistUrlPattern.add(Pattern.compile(url.replaceAll("\\*\\*", "(.*?)"), Pattern.CASE_INSENSITIVE)));
        }
    }
}
