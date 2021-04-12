package com.qianjing.collect.dto;

import com.qianjing.collect.domain.User;

public class RegisterDto {
    private User user;
    private String code;
    private String localCode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }
}
