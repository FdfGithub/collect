package com.qianjing.collect.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
//    task_id  task_name  task_desc  task_deadline  user_id  create_time  doc_types
    private Integer taskId;
    private String taskName;
    private String taskDesc;
    private String formInputs;
    private LocalDateTime taskDeadline;
    private LocalDateTime createTime;
    private String docTypes;
    private User user;//收集创建者
    private List<Collect> collects;
    private int status;
    private String zipUrl;
    private String commitUrl;

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getZipUrl() {
        return zipUrl;
    }

    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl;
    }

    public String getFormInputs() {
        return formInputs;
    }

    public void setFormInputs(String formInputs) {
        this.formInputs = formInputs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Collect> getCollects() {
        return collects;
    }

    public void setCollects(List<Collect> collects) {
        this.collects = collects;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public LocalDateTime getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(LocalDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(String docTypes) {
        this.docTypes = docTypes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
