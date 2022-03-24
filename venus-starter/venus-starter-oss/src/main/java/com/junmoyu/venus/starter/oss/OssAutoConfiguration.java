package com.junmoyu.venus.starter.oss;

import com.junmoyu.venus.starter.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss 自动化配置
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(value = OssProperties.OSS_PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
public class OssAutoConfiguration {

    private final OssProperties properties;

    @Bean
    @ConditionalOnMissingBean(OssService.class)
    @ConditionalOnProperty(name = "oss.enable", havingValue = "true", matchIfMissing = true)
    public OssService ossService() {
        return new OssService(properties);
    }
}
