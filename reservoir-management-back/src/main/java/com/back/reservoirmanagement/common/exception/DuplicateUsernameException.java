package com.back.reservoirmanagement.common.exception;

/**
 * 重复用户名
 */
public class DuplicateUsernameException extends BaseException{
    public DuplicateUsernameException(String msg){
        super(msg);
    }
}
