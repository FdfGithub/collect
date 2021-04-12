package com.qianjing.collect.service;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.domain.Collect;

import java.util.List;

public interface CollectService {
    Response<Void> addCollect(Collect collect);

    Response<List<Collect>> queryCollects(Integer taskId);

    Response<Void> removeCollect(Integer collectId);

    Response<Collect> queryCollect(Integer userId,Integer taskId);
}
