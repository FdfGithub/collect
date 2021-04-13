package com.qianjing.collect.service.impl;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.vo.TaskVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ObjectUtil objectUtil;

    @Override
    public Response<TaskVo> publish(Task task) {
        int count = taskMapper.insertTask(task);
        if (count > 0) {
            return Response.returnSuccess(objectUtil.assembleTaskVo(task), "发布成功");
        }
        throw new OutException("发布失败");
    }

    @Override
    public Response<List<TaskVo>> queryTasks(Integer userId) {
        List<Task> tasks = taskMapper.selectTasksByUserId(userId);
        if (ObjectUtils.isEmpty(tasks)) {
            return Response.returnSuccess(null, "你还没有发布任何收集任务");
        }
        return Response.returnSuccess(objectUtil.assembleTaskVos(tasks), "查询收集任务成功");
    }

    @Override
    public Response<Task> queryTask(Integer taskId) {
        Task task = taskMapper.selectTaskByTaskId(taskId);
        if (task == null) {
            throw new OutException("当前收集任务已经被删除");
        }
        return Response.returnSuccess(task, "查询收集任务信息成功");
    }

    @Override
    public Response<Void> updateStatus(Integer taskId) {
        int count = taskMapper.updateStatus(taskId);
        if (count > 0) {
            return Response.returnSuccess(null, null);
        }
        throw new InException("修改收集任务状态失败：" + taskId);
    }

    @Override
    public Response<Void> addZipUrl(Integer taskId, String zipUrl) {
        int count = taskMapper.updateZipUrl(taskId, zipUrl);
        if (count > 0){
            return Response.returnSuccess(null,null);
        }
        throw new InException("添加压缩URL失败：" + taskId);
    }
}
