package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.exception.BaseDataException;
import com.school.manager.mapper.UserMapper;
import com.school.manager.model.ResultType;
import com.school.manager.model.User;
import com.school.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jack
 * @date 2020/3/17 17:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> queryUserList(User user, PageInfo pageInfo) {
        PageInfo<User> carPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            if (user != null && StringUtils.isNotBlank(user.getName())) {
                criteria.andEqualTo("name", user.getName());
            }
         //   criteria.andNotEqualTo("name","admin");
            PageHelper.startPage(currentPage, pageSize);
            List<User> users = userMapper.selectByExample(example);
            carPageInfo = new PageInfo<>(users);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "UserService error at func(queryUserList) " + e);
            throw e;
        }
        return carPageInfo;
    }

    @Override
    public void add(User user) {
        try {
            userMapper.insert(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            User user1 = queryByName(user.getName());
            if (user1 != null) {
                user.setId(user1.getId());
                userMapper.updateByPrimaryKeySelective(user);
            }else {
                throw new BaseDataException(ResultType.USER_NOT_EXIST);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            userMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User queryByName(String name) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        try {
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
                User user = userMapper.selectOneByExample(example);
                return user;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePayPwd(User user) {
        try {
            User user1 = queryByName(user.getName());
            userMapper.updateByPrimaryKeySelective(user1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User queryUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

}
