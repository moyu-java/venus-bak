package com.junmoyu.venus.starter.core.model.dto;

/**
 * 响应状态码
 *
 * @author moyu.jun
 * @date 2022/3/13
 */
public class ResponseCode {

    /**
     * The constant SUCCESSFUL.
     */
    public static final int SUCCESS = 200;

    /**
     * The constant ERROR.
     */
    public static final int ERROR = 500;

    /**
     * The constant NOT_FOUND_EXCEPTION.
     */
    public static final int NOT_FOUND_EXCEPTION = 404;

    /**
     * The constant TOKEN_ERROR.
     */
    public static final int TOKEN_ERROR = 600;

    /**
     * The constant TOKEN_NO_PERMISSION.
     */
    public static final int TOKEN_NO_PERMISSION = 601;
}
