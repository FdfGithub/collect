package com.qianjing.collect.dao;

import com.qianjing.collect.domain.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectMapper {
    List<Collect> selectCollectsByTaskId(Integer taskId);

    int insertCollect(Collect collect);

    @Delete("delete from tb_collect where collect_id = #{collectId}")
    int deleteCollectByCollectId(Integer collectId);

    @Select("select * from tb_collect where collect_id = #{collectId}")
    Collect selectCollectById(Integer collectId);
}
