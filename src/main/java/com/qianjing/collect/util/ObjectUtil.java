package com.qianjing.collect.util;

import com.qianjing.collect.comm.Const;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.vo.TaskVo;
import com.qianjing.collect.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {

    public static <T> T assembleObject(Object source,Class<T> clazz) {
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

    public static TaskVo assembleTaskVo(Task task){
        TaskVo taskVo = assembleObject(task, TaskVo.class);
        if (task.getCreateTime() != null){
            taskVo.setCreateTime(DateTimeUtil.formatDateTime(task.getCreateTime()));
        }
        if (task.getTaskDeadline() != null){
            taskVo.setTaskDeadline(DateTimeUtil.formatDateTime(task.getTaskDeadline()));
        }
        taskVo.setCreator(assembleObject(task.getUser(), UserVo.class));
        taskVo.setStatusName(Const.TaskStatus.valueOf(task.getStatus()));
        taskVo.setFormInputs(task.getFormInputs().split(","));
        return taskVo;
    }

    public static List<TaskVo> assembleTaskVos(List<Task> tasks){
        List<TaskVo> taskVos = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            taskVos.add(assembleTaskVo(task));
        }
        return taskVos;
    }
}
