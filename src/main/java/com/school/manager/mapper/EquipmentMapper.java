package com.school.manager.mapper;

import com.school.manager.model.Equipment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface EquipmentMapper extends Mapper<Equipment> {

    @Select("select status,count(status) num ,sum(price) total from equipment group by status")
    List<Equipment> queryByGroup();
}
