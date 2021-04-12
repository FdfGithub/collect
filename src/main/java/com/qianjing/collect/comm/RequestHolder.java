package com.qianjing.collect.comm;

import javax.servlet.http.HttpSession;

/**
 * 获取HttpSession
 */
public class RequestHolder {
    private final static ThreadLocal<HttpSession> threadLocal = new ThreadLocal<>();

    public static HttpSession get(){
        return threadLocal.get();
    }

    public static void add(HttpSession session){
        threadLocal.set(session);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
