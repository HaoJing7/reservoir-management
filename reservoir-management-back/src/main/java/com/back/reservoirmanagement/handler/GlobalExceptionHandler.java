package com.back.reservoirmanagement.handler;

import com.back.reservoirmanagement.common.constant.MessageConstant;
import com.back.reservoirmanagement.common.exception.BaseException;
import com.back.reservoirmanagement.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常（BaseException是上面自定义异常的父类）
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result<?> exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获数据库用户名唯一异常
     * 该异常在SQLException下，BaseException不是SQLException的父类，因此sql异常不会被上面捕获
     * @Param
     * @Return
     **/
    @ExceptionHandler
    public Result<?> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        // Duplicate entry '与明程飞' for key 'employee.idx_username' 异常信息
        log.error("异常信息：{}", ex.getMessage());
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] strings = message.split(" ");
            String username = strings[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
