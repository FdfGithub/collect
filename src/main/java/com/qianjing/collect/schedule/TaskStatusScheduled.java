package com.qianjing.collect.schedule;

import com.qianjing.collect.domain.Task;
import com.qianjing.collect.domain.User;
import com.qianjing.collect.properties.FilePathProperties;
import com.qianjing.collect.service.TaskService;
import com.qianjing.collect.service.UserService;
import com.qianjing.collect.util.EmailUtil;
import com.qianjing.collect.util.FileUtil;
import com.qianjing.collect.util.ScheduleUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.Objects;

public class TaskStatusScheduled extends TaskScheduled {

    private final ApplicationContext context;

    private final Integer taskId;

    public TaskStatusScheduled(String id, Integer taskId, ApplicationContext context) {
        super(id);
        this.taskId = taskId;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            TaskService taskService = context.getBean(TaskService.class);
            FilePathProperties properties = context.getBean(FilePathProperties.class);
            // 1.修改状态
            taskService.updateStatus(taskId);
            // 2.压缩taskId目录下的所有文件到一个压缩文件中
            Task task = taskService.queryTask(taskId).getData();
            String filename = taskId + File.separator + task.getTaskName() + ".zip";
            String path = properties.getRootPath() + filename;
            File parent = new File(properties.getRootPath() + taskId);
            File savedFile = new File(path);
            EmailUtil emailUtil = context.getBean(EmailUtil.class);
            UserService userService = context.getBean(UserService.class);
            User user = userService.queryUser(task.getUser().getUserId()).getData();
            File[] files = parent.listFiles();
            if (ObjectUtils.isEmpty(files)){
                emailUtil.sendText(user.getEmail(),task.getTaskName(),"收集时间已经到了,但是还没有一个人上交");
                return;
            }
            FileUtil.zipFiles(files, savedFile);
            //3.把压缩文件的url存储到数据库中
            taskService.addZipUrl(taskId, filename);
            //4.读取压缩文件，发送给任务创建者的邮箱里
            emailUtil.sendMime(user.getEmail(),task.getTaskName(),"",savedFile);
            //5.TODO 删除文件
        } finally {
            ScheduleUtil.cancel(id);
        }
    }

}
