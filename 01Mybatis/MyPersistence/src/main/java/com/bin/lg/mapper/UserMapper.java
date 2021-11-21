package com.bin.lg.mapper;

import com.bin.lg.domain.User;

import java.util.List;

/**
 * @author bin72
 * @date 2021/11/21 23:36
 */
public interface UserMapper {
    List<User> selectList(User user);

    User selectOne(User user);
}
