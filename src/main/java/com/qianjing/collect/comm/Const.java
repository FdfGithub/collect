package com.qianjing.collect.comm;

import com.qianjing.collect.exception.InException;

public class Const {


    public enum TaskStatus{
        COLLECTING(0,"收集中"),
        COLLECTED(1,"已经收集结束");

        private int status;
        private String value;

        TaskStatus(int status, String value) {
            this.status = status;
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String valueOf(int status){
            for (TaskStatus taskStatus : TaskStatus.values()) {
                if (taskStatus.getStatus() == status){
                    return taskStatus.getValue();
                }
            }
            throw new InException("TaskStatus值不存在：" + status);
        }
    }

    public interface UserRole{
        int ADMIN = 1;
        int GENERAL = 0;
    }
}
