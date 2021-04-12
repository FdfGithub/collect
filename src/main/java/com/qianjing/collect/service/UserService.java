package com.qianjing.collect.service;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.dto.RegisterDto;
import com.qianjing.collect.vo.TaskVo;
import com.qianjing.collect.vo.UserVo;

import java.util.List;

public interface UserService {
    Response<UserVo> login(String email, String userPwd);

    //在注册之前检查验证码的正确性
    Response<Void> register(RegisterDto dto);

    void getCode(String email);

    Response<User> queryUser(Integer userId);
}
