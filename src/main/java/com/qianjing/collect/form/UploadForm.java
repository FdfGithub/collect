package com.qianjing.collect.form;

import com.qianjing.collect.annotation.Check;
import com.qianjing.collect.annotation.NotNull;

@Check
public class UploadForm {
    @NotNull
    private String submitNames;
    @NotNull
    private Integer taskId;

    public String getSubmitNames() {
        return submitNames;
    }

    public void setSubmitNames(String submitNames) {
        this.submitNames = submitNames;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
