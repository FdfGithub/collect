package com.qianjing.collect.aspect;

import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.schedule.TaskStatusScheduled;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.util.DateTimeUtil;
import com.qianjing.collect.util.ScheduleUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Aspect
public class TaskAspect implements ApplicationContextAware {
    @Pointcut("execution(* com.qianjing.collect.service.impl.TaskServiceImpl.publish(com.qianjing.collect.domain.Task))")
    public void pointcut() {
    }

    private static final String url = "http://39.108.186.224/task/commit/";

    @Autowired
    private TaskMapper taskMapper;

    @AfterReturning("pointcut()")
    public void afterReturn(JoinPoint jp) {
        //任务发布完成后，就创建一个定时任务来定时修改收集任务的状态
        Task task = (Task) jp.getArgs()[0];
        LocalDateTime deadline = task.getTaskDeadline();
        taskMapper.updateCommitUrl(task.getTaskId(),url + task.getTaskId());
        ScheduleUtil.start(new TaskStatusScheduled(UUID.randomUUID().toString(),task.getTaskId(),context)
                ,DateTimeUtil.LocalDateTimeToDate(deadline));
    }

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
