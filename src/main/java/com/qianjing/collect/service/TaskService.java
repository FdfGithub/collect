package com.qianjing.collect.service;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.vo.TaskVo;

import java.util.List;

public interface TaskService {
    Response<TaskVo> publish(Task task);

    Response<List<TaskVo>> queryTasks(Integer userId);

    Response<Task> queryTask(Integer taskId);

    Response<Void> updateStatus(Integer taskId);

    Response<Void> addZipUrl(Integer taskId, String zipUrl);
}
