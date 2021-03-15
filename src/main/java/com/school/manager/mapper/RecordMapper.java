package com.school.manager.mapper;

import com.school.manager.model.Record;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface RecordMapper extends Mapper<Record> {

    @Select("select * from record group by user_id")
    List<Record> queryRecordListByGroup();
}
