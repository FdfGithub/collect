package com.qianjing.collect.service.impl;

import com.qianjing.collect.comm.Response;
import com.qianjing.collect.dao.CollectMapper;
import com.qianjing.collect.domain.Collect;
import com.qianjing.collect.exception.OutException;
import com.qianjing.collect.service.CollectService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;


    //TODO 查询出来的docUrl必须加url-path的值
    @Value("${collect.work.url-path}")
    private String urlPath;

    @Override
    public Response<Void> addCollect(Collect collect) {
        int count = collectMapper.insertCollect(collect);
        if (count > 0){
            return Response.returnSuccess(null, "上交成功");
        }
        throw new OutException("上交失败");
    }

    @Override
    public Response<List<Collect>> queryCollects(Integer taskId) {
        List<Collect> collects = collectMapper.selectCollectsByTaskId(taskId);
        if (ObjectUtils.isEmpty(collects)){
            return Response.returnSuccess(null,"暂时还没有任何人上交");
        }
        return Response.returnSuccess(collects,"查询上交的文档成功");
    }

    @Override
    public Response<Void> removeCollect(Integer collectId) {
        int count = collectMapper.deleteCollectByCollectId(collectId);
        if (count>0){
            return Response.returnSuccess(null,null);
        }
        throw new OutException("撤销失败");
    }

//    @Override
//    public Response<Collect> queryCollect(Integer userId, Integer taskId) {
//        Collect collect = collectMapper.selectCollectByUserIdAndTaskId(userId, taskId);
//        if (ObjectUtils.isEmpty(collect)){
//            throw new OutException("你还没有提交该收集任务相关文档");
//        }
//        return Response.returnSuccess(collect,"查询成功");
//    }
}
