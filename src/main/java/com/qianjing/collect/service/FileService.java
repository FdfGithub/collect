package com.qianjing.collect.service;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.form.UploadForm;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    //文件上传
    Response<String> upload(MultipartFile file, UploadForm form);

    //文件撤销
    Response<Void> repeal(Integer taskId, Integer collectId);
}
