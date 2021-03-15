package com.school.manager.mapper;

import com.school.manager.model.Borrow;
import com.school.manager.model.Equipment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface BorrowMapper extends Mapper<Borrow> {

    @Select("select b.*,a.id as borrowId,a.borrow_date,a.start_date,a.e_status from borrow a left join equipment b on a.equipment_id=b.id where a.user_id= #{user_id}")
    List<Equipment> queryPageList(@Param("user_id") int user_id);

    @Select("select b.*,a.id as borrowId,a.borrow_date,a.start_date,a.e_status from borrow a left join equipment b on a.equipment_id=b.id where b.name= #{name}")
    List<Equipment> queryPageList1(@Param("name") String name);

    @Select("select b.*,a.id as borrowId,a.borrow_date,a.start_date,a.e_status from borrow a left join equipment b on a.equipment_id=b.id ")
    List<Equipment> queryPageList2();

    @Select("select e_status,count(e_status) num from borrow group by e_status")
    List<Borrow> queryBorrowGroupBy();
}
