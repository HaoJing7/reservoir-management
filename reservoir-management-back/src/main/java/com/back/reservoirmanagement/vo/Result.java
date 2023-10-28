package com.back.reservoirmanagement.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果")
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    // 成功 没有响应数据
    public static<T>  Result<T> success(){
        return new Result<>(20000,"success",null);
    }
    // 成功 有响应数据
    public static<T>  Result<T> success(T data){
        return new Result<>(20000,"success",data);
    }
    // 成功 有响应数据 还有后端通知信息信息
    public static<T>  Result<T> success(T data, String message){
        return new Result<>(20000,message,data);
    }
    // 成功 有后端通知信息
    public static<T>  Result<T> success(String message){
        return new Result<>(20000,message,null);
    }
    // 失败 没有失败的消息
    public static<T>  Result<T> fail(){
        return new Result<>(20001,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(20001,message,null);
    }
}
