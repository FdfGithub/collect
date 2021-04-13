package com.qianjing.collect.service.impl;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.dao.UserMapper;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.dto.RegisterDto;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.service.UserService;
import com.qianjing.collect.util.EmailUtil;
import com.qianjing.collect.util.MD5Util;
import com.qianjing.collect.util.SessionUtil;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.vo.TaskVo;
import com.qianjing.collect.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private ObjectUtil objectUtil;

    @Override
    public Response<UserVo> login(String email, String userPwd) {
        int count = userMapper.selectCount(email, MD5Util.MD5EncodeUtf8(userPwd));
        if (count > 0) {
            //查出当前用户的基本信息
            User user = userMapper.selectUserByEmail(email);
            if (user == null) {
                throw new OutException("用户不存在");
            }
            user.setUserPwd("");
            SessionUtil.set("userId", user.getUserId(), -1);
            return Response.returnSuccess(objectUtil.assembleObject(user,UserVo.class), "登录成功");
        }
        throw new OutException("用户名或密码错误");
    }

    @Override
    public Response<Void> register(RegisterDto dto) {
        User user = dto.getUser();
        user.setUserPwd(MD5Util.MD5EncodeUtf8(user.getUserPwd()));
        int count = userMapper.insertUser(user);
        if (count > 0) {
            return Response.returnSuccess(null, "注册成功");
        }
        throw new RuntimeException("注册失败");
    }

    @Override
    public void getCode(String email) {
        SessionUtil.set("localCode", emailUtil.sendCode(email, "【注册】"), 60 * 5);
    }

    @Override
    public Response<User> queryUser(Integer userId) {
        User user = userMapper.selectUserById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new InException("用户不存在:" + userId);
        }
        return Response.returnSuccess(user,null);
    }
}
