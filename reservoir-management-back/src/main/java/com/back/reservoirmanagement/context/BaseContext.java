package com.back.reservoirmanagement.context;

/**
 * 封装了threadLocal类方法，
 * 目的是让用户id在全局资源中始终可以获取
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
