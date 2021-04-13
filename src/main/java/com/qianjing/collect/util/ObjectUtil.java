package com.qianjing.collect.util;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.properties.FilePathProperties;
import com.qianjing.collect.vo.CollectVo;
import com.qianjing.collect.vo.TaskVo;
import com.qianjing.collect.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.module.Configuration;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ObjectUtil {

    public <T> T assembleObject(Object source,Class<T> clazz) {
        try{
            Constructor<T> constructor = clazz.getConstructor();
            T target = constructor.newInstance();
            BeanUtils.copyProperties(source,target);
            return target;
        }catch (Exception e){
            e.printStackTrace();
            throw new InException("对象转换错误：" + source.getClass().toString() + "->" + clazz.toString());
        }
    }

    public TaskVo assembleTaskVo(Task task){
        TaskVo taskVo = assembleObject(task, TaskVo.class);
        if (task.getCreateTime() != null){
            taskVo.setCreateTime(DateTimeUtil.formatDateTime(task.getCreateTime()));
        }
        if (task.getTaskDeadline() != null){
            taskVo.setTaskDeadline(DateTimeUtil.formatDateTime(task.getTaskDeadline()));
        }
        if (ObjectUtils.isNotEmpty(task.getCollects())){
            taskVo.setCollects(assembleCollectVos(task.getCollects()));
        }
        if (StringUtils.isNotEmpty(task.getZipUrl())){
            taskVo.setZipUrl(properties.getUrlPath() + task.getZipUrl());
        }
        taskVo.setCreator(assembleObject(task.getUser(), UserVo.class));
        taskVo.setStatusName(Const.TaskStatus.valueOf(task.getStatus()));
        taskVo.setFormInputs(task.getFormInputs().split(","));
        return taskVo;
    }

    @Autowired
    private FilePathProperties properties;

    public CollectVo assembleCollectVo(Collect collect){
        CollectVo vo = assembleObject(collect, CollectVo.class);
        vo.setCollectName(collect.getDocUrl().split("/")[1]);
        vo.setDocUrl(properties.getUrlPath() + vo.getDocUrl());
        return vo;
    }

    public List<TaskVo> assembleTaskVos(List<Task> tasks){
        List<TaskVo> taskVos = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            taskVos.add(assembleTaskVo(task));
        }
        return taskVos;
    }

    public List<CollectVo> assembleCollectVos(List<Collect> collects){
        List<CollectVo> collectVos = new ArrayList<>(collects.size());
        for (Collect collect : collects) {
            collectVos.add(assembleCollectVo(collect));
        }
        return collectVos;
    }


}
