package com.junmoyu.venus.starter.core.model.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一返回数据结构
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -7542992242245664910L;

    private Integer code;

    private String message;

    private T data;

    /**
     * Instantiates a new response.
     */
    public Response() {
    }

    /**
     * Instantiates a new response.
     *
     * @param code    the code
     * @param message the message
     * @param data    result data
     */
    public Response(final Integer code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * return success.
     *
     * @param <T> the data class
     * @return {@link Response}
     */
    public static <T> Response<T> success() {
        return success(null);
    }

    /**
     * return success.
     *
     * @param data result data
     * @param <T>  the data class
     * @return {@link Response}
     */
    public static <T> Response<T> success(final T data) {
        return new Response<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, data);
    }

    /**
     * return failure.
     *
     * @param <T> the data class
     * @return {@link Response}
     */
    public static <T> Response<T> failure() {
        return failure(ResponseCode.ERROR, ResponseMessage.FAILED);
    }

    /**
     * return failure.
     *
     * @param message error message
     * @param <T>     the data class
     * @return {@link Response}
     */
    public static <T> Response<T> failure(final String message) {
        return failure(ResponseCode.ERROR, message);
    }

    /**
     * return failure.
     *
     * @param message error message
     * @param data    result data
     * @param <T>     the data class
     * @return {@link Response}
     */
    public static <T> Response<T> failure(final String message, final T data) {
        return failure(ResponseCode.ERROR, message, data);
    }

    /**
     * return failure.
     *
     * @param code    error code
     * @param message error message
     * @param <T>     the data class
     * @return {@link Response}
     */
    public static <T> Response<T> failure(final Integer code, final String message) {
        return failure(code, message, null);
    }

    /**
     * return failure.
     *
     * @param code    error code
     * @param message error message
     * @param data    result data
     * @param <T>     the data class
     * @return {@link Response}
     */
    public static <T> Response<T> failure(final Integer code, final String message, final T data) {
        return new Response<>(code, message, data);
    }

    /**
     * return timeout.
     *
     * @param message error message
     * @param <T>     the data class
     * @return {@link Response}
     */
    public static <T> Response<T> timeout(final String message) {
        return failure(HttpStatus.REQUEST_TIMEOUT.value(), message, null);
    }

    /**
     * Gets the value of code.
     *
     * @return the value of code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code code
     */
    public void setCode(final Integer code) {
        this.code = code;
    }

    /**
     * Gets the value of message.
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the value of data.
     *
     * @return the value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data data
     */
    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Response)) {
            return false;
        }
        Response<?> response = (Response<?>) o;
        return Objects.equals(code, response.code) && Objects.equals(message, response.message) && Objects.equals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        return "Response{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
