package com.qianjing.collect.vo;

import com.qianjing.collect.domain.Collect;

import java.util.List;

public class TaskVo {
    private Integer taskId;
    private String taskName;
    private String taskDesc;
    private String taskDeadline;
    private String createTime;
    private String docTypes;
    private String[] formInputs;
    private UserVo creator;//收集任务的创建者
    private List<CollectVo> collects;
    private String statusName;
    private int status;
    private String commitUrl;
    private String zipUrl;

    public String getZipUrl() {
        return zipUrl;
    }

    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl;
    }

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(String docTypes) {
        this.docTypes = docTypes;
    }

    public String[] getFormInputs() {
        return formInputs;
    }

    public void setFormInputs(String[] formInputs) {
        this.formInputs = formInputs;
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

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public UserVo getCreator() {
        return creator;
    }

    public void setCreator(UserVo creator) {
        this.creator = creator;
    }

    public List<CollectVo> getCollects() {
        return collects;
    }

    public void setCollects(List<CollectVo> collects) {
        this.collects = collects;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskVo{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskDeadline='" + taskDeadline + '\'' +
                ", createTime='" + createTime + '\'' +
                ", docTypes='" + docTypes + '\'' +
                ", creator=" + creator +
                ", collects=" + collects +
                ", status='" + status + '\'' +
                '}';
    }
}
