package com.junmoyu.venus.starter.core.util;

import com.junmoyu.venus.starter.core.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * 链路追踪工具类
 *
 * @author moyu.jun
 * @date 2021/12/25
 */
public class TraceUtils {

    /**
     * 从 header 和参数中获取 traceId
     * 从前端传入数据
     *
     * @param request 　HttpServletRequest
     * @return traceId
     */
    public static String getTraceId(HttpServletRequest request) {
        String traceId = request.getParameter(CommonConstants.VENUS_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = request.getHeader(CommonConstants.VENUS_TRACE_ID);
        }
        return traceId;
    }

    /**
     * 传递 traceId 至 MDC
     *
     * @param traceId 　跟踪 ID
     */
    public static void mdcTraceId(String traceId) {
        if (StringUtils.isNotBlank(traceId)) {
            MDC.put(CommonConstants.LOG_TRACE_ID, traceId);
        }
    }
}
