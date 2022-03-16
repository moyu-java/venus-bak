package com.junmoyu.venus.example.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos config example controller.
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/nacos/config")
@RequiredArgsConstructor
public class NacosConfigController {

    @Value("${jasypt.encryptor.password}")
    private String password;

    @Value("${venus.author}")
    private String venusAuthor;

    private final NacosConfigManager nacosConfigManager;

    private final ObjectMapper objectMapper;

    /**
     * jasypt.encryptor.password refresh is false.
     *
     * @return jasypt.encryptor.password value.
     */
    @GetMapping("/common/password")
    public String getPassword() {
        return password;
    }

    /**
     * venus.author refresh is true.
     *
     * @return venus.author value.
     */
    @GetMapping("/venus/author")
    public String getVenusAuthor() {
        return venusAuthor;
    }

    /**
     * get json file from nacos config.
     *
     * @return json file.
     */
    @GetMapping("/json")
    public JsonNode getNacosJson() {
        try {
            ConfigService configService = nacosConfigManager.getConfigService();

            String config = configService.getConfig("venus-example-nacos-config-json.json", "VENUS_GROUP", 10000);
            JsonNode jsonNode = objectMapper.readTree(config);
            if (config != null) {
                return jsonNode;
            }
        } catch (NacosException | JsonProcessingException e) {
            log.error("get nacos json file failed.", e);
        }
        return null;
    }
}
