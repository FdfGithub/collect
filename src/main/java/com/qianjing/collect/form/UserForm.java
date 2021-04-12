package com.qianjing.collect.form;

import com.qianjing.collect.annotation.Check;
import com.qianjing.collect.annotation.NotNull;

@Check
public class UserForm {
    @NotNull
    private String userName;

    @NotNull
    private String userPwd;

    @NotNull
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
