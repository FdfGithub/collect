package com.qianjing.collect.interceptor;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.dao.UserMapper;
import com.qianjing.collect.service.UserService;
import com.qianjing.collect.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PowerIntercept implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = SessionUtil.get("userId", Integer.class);
        int userRole = userMapper.selectUserById(userId).getUserRole();
        return userRole == Const.UserRole.ADMIN;
    }
}
