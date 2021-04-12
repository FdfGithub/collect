package com.qianjing.collect.form;

import com.qianjing.collect.annotation.Check;
import com.qianjing.collect.annotation.NotNull;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Check
public class TaskForm {
    @NotNull
    private String taskName;
    private String taskDesc;
    @NotNull
    private LocalDateTime taskDeadline;
    private String docTypes;
    @NotNull
    private String formInputs;

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

    public String getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(String docTypes) {
        this.docTypes = docTypes;
    }

    public void setFormInputs(String formInputs) {
        this.formInputs = formInputs;
    }

    public String getFormInputs() {
        return formInputs;
    }
}
