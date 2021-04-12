package com.qianjing.collect.schedule;

public abstract class TaskScheduled implements Runnable {

    public final String id;

    protected TaskScheduled(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void run() {}
}
