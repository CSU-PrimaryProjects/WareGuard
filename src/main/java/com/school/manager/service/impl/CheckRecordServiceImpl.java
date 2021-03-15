package com.school.manager.service.impl;/**
 * @author jack
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13 22:12
 */

import com.school.manager.mapper.CheckRecordMapper;
import com.school.manager.model.CheckRecord;
import com.school.manager.service.CheckRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TODO
 *
 * @author jack
 * @version 1.0
 * @date 2021/3/13 22:12
 */
@Service
public class CheckRecordServiceImpl implements CheckRecordService {

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Override
    public void add(CheckRecord model) {
        CheckRecord checkRecord = queryByEId(model.getE_id());
        if (checkRecord == null) {
            checkRecordMapper.insert(model);
        }
    }

    @Override
    public void delete(Integer e_id) {
        Example example = new Example(CheckRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("e_id",e_id);
        checkRecordMapper.deleteByExample(example);
    }

    @Override
    public CheckRecord queryByEId(Integer e_id) {
        Example example = new Example(CheckRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("e_id",e_id);
        CheckRecord checkRecord = checkRecordMapper.selectOneByExample(example);
        return checkRecord;
    }

    @Override
    public int queryCheckRecordAllNum() {
        List<CheckRecord> checkRecords = checkRecordMapper.selectAll();
        return checkRecords.size();
    }
}
