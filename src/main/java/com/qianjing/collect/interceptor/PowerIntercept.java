package com.qianjing.collect.interceptor;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.dao.UserMapper;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.service.UserService;
import com.qianjing.collect.util.SessionUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PowerIntercept implements HandlerInterceptor {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] split = request.getServletPath().split("/");
        if (ObjectUtils.isEmpty(split) || split.length < 4){
            response.sendError(404,"访问路径错误");
            return false;
        }
        Integer taskId = Integer.parseInt(split[2]);
        Integer userId = SessionUtil.get("userId", Integer.class);
        Task task = taskMapper.selectTaskByTaskId(taskId);
        if (ObjectUtils.isEmpty(task)){
            response.sendError(404,"访问路径错误");
            return false;
        }
        if (!userId.equals(task.getUser().getUserId())){
            response.sendError(403,"没有权限");
            return false;
        }
        return true;
    }
}
