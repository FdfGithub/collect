package com.qianjing.collect.dao;

import com.qianjing.collect.domain.Task;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TaskMapper {
    Task selectTaskByTaskId(Integer taskId);

    List<Task> selectTasksByUserId(Integer userId);

    int insertTask(Task task);

    int updateStatus(Integer taskId);

    @Update("update tb_task set zip_url = #{zipUrl} where task_id = #{taskId}")
    int updateZipUrl(Integer taskId,String zipUrl);

    @Update("update tb_task set commit_url = #{commitUrl} where task_id = #{taskId}")
    int updateCommitUrl(Integer taskId,String commitUrl);
}
