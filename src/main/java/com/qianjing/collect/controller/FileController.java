package com.qianjing.collect.controller;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.form.UploadForm;
import com.qianjing.collect.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Response<String> upload(MultipartFile file, UploadForm form) {
        return fileService.upload(file, form);
    }


    @DeleteMapping("/repeal")
    public Response<Void> repeal(Integer taskId, Integer collectId){
        return fileService.repeal(taskId, collectId);
    }
}
