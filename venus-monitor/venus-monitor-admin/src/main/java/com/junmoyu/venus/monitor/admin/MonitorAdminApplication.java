package com.junmoyu.venus.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控服务启动器
 *
 * @author moyu.jun
 * @date 2022/3/19
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminApplication.class, args);
    }
}
