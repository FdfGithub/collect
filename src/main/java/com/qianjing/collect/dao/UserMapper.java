package com.qianjing.collect.dao;

import com.qianjing.collect.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUserById(Integer userId);

    User selectUserByEmail(String email);

    int selectCount(@Param("email") String email, @Param("userPwd") String userPwd);

    int insertUser(User user);
}
