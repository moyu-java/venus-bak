package com.junmoyu.venus.starter.core.exception;

import com.junmoyu.venus.starter.core.model.dto.ResponseCode;

/**
 * Venus Exception
 *
 * @author moyu.jun
 * @date 2022/3/13
 */
public class VenusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    /**
     * Instantiates a new venus exception.
     *
     * @param message the message
     */
    public VenusException(final String message) {
        this(ResponseCode.ERROR, message);
    }

    /**
     * Instantiates a new venus exception.
     *
     * @param code    the code
     * @param message the message
     */
    public VenusException(final Integer code, final String message) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new venus exception.
     *
     * @param message the message
     * @param e       the throwable
     */
    public VenusException(final String message, final Throwable e) {
        this(ResponseCode.ERROR, message, e);
    }

    /**
     * Instantiates a new venus exception.
     *
     * @param code    the code
     * @param message the message
     * @param e       the throwable
     */
    public VenusException(final Integer code, final String message, final Throwable e) {
        super(message, e);
        this.code = code;
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
}
