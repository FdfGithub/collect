package com.qianjing.collect.aspect;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.comm.Response;
import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.form.UploadForm;
import com.qianjing.collect.properties.FilePathProperties;
import com.qianjing.collect.service.CollectService;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.util.SessionUtil;
import com.qianjing.collect.vo.TaskVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
public class FileUploadAspect {
    @Pointcut("execution(* com.qianjing.collect.service.impl.FileServiceImpl.*(..))")
    private void pointCut() {
    }

    @Pointcut("execution(* com.qianjing.collect.service.impl.FileServiceImpl.upload(..))")
    private void uploadPointCut(){}

    @Pointcut("execution(* com.qianjing.collect.service.impl.FileServiceImpl.repeal(..))")
    private void repealPointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint jp) {
        //超时检查
        checkTimeout(getTaskId(jp.getArgs()));
    }

    @Autowired
    private CollectService collectService;

    @AfterReturning(returning="response",pointcut = "uploadPointCut()")
    public void uploadPost(JoinPoint jp,Response<String> response){
        //在上传成功后，注册collect信息
        Collect collect = new Collect();
        Integer taskId = getTaskId(getTaskId(jp.getArgs()));
        String docUrl = response.getData();
        Task task = new Task();
        task.setTaskId(taskId);
        collect.setTask(task);
        collect.setDocUrl(docUrl);
        collectService.addCollect(collect);
    }

    @AfterReturning("repealPointCut()")
    public void repealPost(JoinPoint jp){
        //在撤销成功后，需要删除collect信息
        Integer taskId = getTaskId(getTaskId(jp.getArgs()));
        Integer userId = SessionUtil.get("userId", Integer.class);
        //根据用户id和taskId查询collect
        Collect collect = collectService.queryCollect(userId, taskId).getData();
        collectService.removeCollect(collect.getCollectId());
    }

    @Autowired
    private TaskService taskService;

    private Integer getTaskId(Object... args) {
        for (Object arg : args) {
            if (arg instanceof Integer) {
                return (Integer) arg;
            }
            if (arg instanceof UploadForm) {
                return ((UploadForm) arg).getTaskId();
            }
        }
        throw new InException("taskId为空值,无法操作");
    }


    private void checkTimeout(Integer taskId) {
        Task task = taskService.queryTask(taskId).getData();
        if(Const.TaskStatus.COLLECTED.getStatus() == task.getStatus()){
            throw new OutException("你已经超时了");
        }
    }
}
