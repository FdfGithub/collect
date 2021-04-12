package com.qianjing.collect.vo;

public class UserVo {
    private Integer userId;

    private String userName;

    private int UserRole;

    public int getUserRole() {
        return UserRole;
    }

    public void setUserRole(int userRole) {
        UserRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
