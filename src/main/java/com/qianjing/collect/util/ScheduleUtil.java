package com.qianjing.collect.util;

import com.qianjing.collect.schedule.TaskScheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class ScheduleUtil {

    private final static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    private final static Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();

    static {
        threadPoolTaskScheduler.initialize();
    }

    public static void start(TaskScheduled task, Date startTime) {
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(task, startTime);
        scheduledFutureMap.put(task.getId(), scheduledFuture);
    }

    public static void cancel(String id) {
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(id);
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }
        scheduledFutureMap.remove(id);
    }

    public static void reset(TaskScheduled task, Date startTime) {
        cancel(task.getId());
        start(task, startTime);
    }
}
