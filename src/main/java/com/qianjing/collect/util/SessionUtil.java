package com.qianjing.collect.util;

import com.qianjing.collect.comm.RequestHolder;
import com.qianjing.collect.exception.HttpSessionNoExistValueException;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void set(final String name,Object data,int activeTime){
        HttpSession session = RequestHolder.get();
        if (session == null){
            throw new OutException("未登录");
        }
        session.setAttribute(name, data);
        session.setMaxInactiveInterval(activeTime);
    }

    public static <T> T get(String name,Class<T> clazz){
        HttpSession session = RequestHolder.get();
        if (session == null){
            throw new InException("HttpSession对象不存在");
        }
        Object obj = session.getAttribute(name);
        if (obj == null){
            throw new HttpSessionNoExistValueException(name);
        }
        return clazz.cast(obj);
    }


    public static void check(String name){
        if (RequestHolder.get().getAttribute(name) == null){
            throw new HttpSessionNoExistValueException(name);
        }
    }
}
