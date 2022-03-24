package com.junmoyu.venus.starter.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aws 配置信息
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@Data
@ConfigurationProperties(prefix = OssProperties.OSS_PREFIX)
public class OssProperties {

    public static final String OSS_PREFIX = "oss";

    private Boolean enable;

    /**
     * 是否开启 endpoint
     */
    private Boolean info;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * 预览服务器的URL
     */
    private String preview;

    /**
     * 自定义域名
     */
    private String customDomain;

    /**
     * true path-style nginx 反向代理和 S3 默认支持 pathStyle {http://endpoint/bucketname}
     * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式 {http://bucketname.endpoint}
     */
    private Boolean pathStyleAccess = false;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 区域
     */
    private String region;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

    /**
     * 默认的存储桶名称
     */
    private String bucketName = "venus";
}
