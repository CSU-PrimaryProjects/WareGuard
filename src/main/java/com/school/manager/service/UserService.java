package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.User;

/**
 * @author jack
 * @date 2020/3/17 17:45
 */
public interface UserService {
    PageInfo<User> queryUserList(User result, PageInfo pageInfo);
    void add(User user);
    void update(User user);
    void delete(Integer id);
    User queryByName(String name);
    void updatePayPwd(User user);
    User queryUserById(Integer id);
}
