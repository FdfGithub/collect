package com.qianjing.collect.service.impl;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.dao.CollectMapper;
import com.qianjing.collect.dao.TaskMapper;
import com.qianjing.collect.dao.UserMapper;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.exception.FileException;
import com.qianjing.collect.exception.InException;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.form.UploadForm;
import com.qianjing.collect.service.FileService;
import com.qianjing.collect.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

@Service
public class FileServiceImpl implements FileService {

    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private TaskMapper taskMapper;

    @Value("${collect.work.root-path}")
    private String rootPath;

    @Override
    @Transactional
    public Response<String> upload(MultipartFile file, UploadForm form) {
        Task task = taskMapper.selectTaskByTaskId(form.getTaskId());
        if (task == null) {
            throw new OutException("当前收集任务已经被删除");
        }
        try {
            //1. 检查文件类型是否符合task的docTypes
            String suffix = FileUtil.getSuffix(file.getOriginalFilename());
            if (isPass(suffix, task.getDocTypes())) {
                //符合要求，可以上传文件
                //2. 重命名文件名称：学号+姓名+task标题  存储路径：taskId/学号/
                String filename = form.getSubmitNames().replace(",", "-") + "-" + task.getTaskName() +
                        "." + suffix;
                File parent = new File(rootPath + task.getTaskId());
                File save = new File(parent, filename);
                if (save.exists()) {
                    throw new OutException("你已经提交过了(如果想重新提交，请先撤销之前提交的记录)");
                }
                //直接返回上传成功，然后再开一个线程自己去完成上传操作
                Executors.newCachedThreadPool().execute(() -> {
                    if (!parent.exists() && !parent.mkdirs()) {
                        throw new InException("文件创建失败：" + parent.getAbsolutePath());
                    }
                    try {
                        file.transferTo(save);
                        logger.info(filename + "文件成功上传到：{}", parent.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                String url = task.getTaskId() + "/" + filename;
                return Response.returnSuccess(url, "上传成功");
            }
            throw new OutException("文件类型不符合要求");
        } catch (FileException e) {
            e.printStackTrace();
            throw new OutException("上传失败");
        }
    }

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private UserMapper userMapper;

    //撤销操作必须注册登录  filename格式：1801130067-傅德帆-离散数学5.doc
    @Override
    public Response<Void> repeal(Integer taskId, Integer collectId) {
        //1. 确定文件在不在
        Collect collect = collectMapper.selectCollectById(collectId);
        File file = new File(rootPath, collect.getDocUrl());
        if (file.exists() && file.delete()) {
            //2. 在的话删除，不在报错
            return Response.returnSuccess(null, "撤销成功");
        }
        throw new OutException("撤销失败");
    }

    //判断collect上传的文件类型是否符合要求
    private boolean isPass(String currType, String docTypes) {
        for (String type : docTypes.split(",")) {
            if (type.equals(currType)) {
                return true;
            }
        }
        return false;
    }
}
