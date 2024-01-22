package com.back.reservoirmanagement.common.exception;

/**
 * 业务异常
 * 给别的异常进行继承
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
