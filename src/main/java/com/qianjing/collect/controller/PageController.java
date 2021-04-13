package com.qianjing.collect.controller;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.util.ObjectUtil;
import com.qianjing.collect.util.SessionUtil;
import com.qianjing.collect.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ObjectUtil objectUtil;

    @GetMapping("/task/commit/{taskId}")
    public String returnTaskCommitPage(@PathVariable Integer taskId, Model model) {
        //查出task的信息
        Task task = taskService.queryTask(taskId).getData();
        TaskVo taskVo = objectUtil.assembleTaskVo(task);
        model.addAttribute("task", taskVo);
        return "commit";
    }


    @GetMapping("/task/publish/page")
    public String returnTaskPublishPage() {
        return "publish";
    }


    @GetMapping("/task/collects/page")
    public String returnTaskCollectsPage(Model model) {
        Integer userId = SessionUtil.get("userId", Integer.class);
        List<TaskVo> tasks = taskService.queryTasks(userId).getData();
        model.addAttribute("tasks",tasks);
        return "collects";
    }
}
