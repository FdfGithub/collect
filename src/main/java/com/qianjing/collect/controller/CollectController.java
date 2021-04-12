package com.qianjing.collect.controller;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.domain.Task;
import com.qianjing.collect.service.CollectService;
import com.qianjing.collect.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

}
