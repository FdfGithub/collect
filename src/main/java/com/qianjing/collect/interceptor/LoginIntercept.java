package com.qianjing.collect.interceptor;

import com.qianjing.collect.exception.HttpSessionNoExistValueException;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.IllegalFormatCodePointException;

@Component
public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            SessionUtil.check("userId");
            return true;
        } catch (HttpSessionNoExistValueException e) {
            e.printStackTrace();
            response.sendRedirect("/login.html");
            return false;
        }
    }
}
