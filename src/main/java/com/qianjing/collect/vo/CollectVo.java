package com.qianjing.collect.vo;

import com.qianjing.collect.domain.Task;

import java.time.LocalDateTime;

public class CollectVo {

    private Integer collectId;
    private Task task;
    private String docUrl;
    private LocalDateTime createTime;
    private int status;
    private String collectName;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    @Override
    public String toString() {
        return "CollectVo{" +
                "collectId=" + collectId +
                ", task=" + task +
                ", docUrl='" + docUrl + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", collectName='" + collectName + '\'' +
                '}';
    }
}
