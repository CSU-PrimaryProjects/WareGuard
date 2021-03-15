package com.school.manager.mapper;

import com.school.manager.model.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author jack
 * @date 2020/3/17 15:39
 */
@Component
public interface UserMapper extends Mapper<User> {

}
