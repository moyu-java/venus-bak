package com.junmoyu.venus.starter.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Spring 工具类
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@Slf4j
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的 ApplicationContext.
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 实现 ApplicationContextAware 接口, 注入 Context 到静态变量中.
     *
     * @param applicationContext applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 从静态变量 applicationContext 中取得 Bean, 自动转型为所赋值对象的类型.
     *
     * @param name 实例名称
     * @return 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量 applicationContext 中取得 Bean, 自动转型为所赋值对象的类型.
     *
     * @param requiredType 实例类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除 SpringContextHolder 中的 ApplicationContext 为 Null.
     */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除 SpringContextHolder 中的 ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 发布事件
     *
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }

    /**
     * 实现 DisposableBean 接口, 在 Context 关闭时清理静态变量.
     */
    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }

}
