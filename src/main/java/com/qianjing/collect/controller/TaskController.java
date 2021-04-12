package com.qianjing.collect.controller;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.form.TaskForm;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.util.SessionUtil;
import com.qianjing.collect.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/publish")
    public Response<TaskVo> publish(TaskForm form) {
        Task task = ObjectUtil.assembleObject(form, Task.class);
        User user = new User();
        user.setUserId(SessionUtil.get("userId", Integer.class));
        task.setUser(user);
        return taskService.publish(task);
    }
}
