package com.bin.lg.mapper;

import com.bin.lg.domain.User;

import java.util.List;

/**
 * IUserMapper
 *
 * @author Administrator
 * @date 2021/11/22 15:00
 */
public interface IUserMapper {
    /**
     * 查询所有
     * @return 查询结果
     */
    List<User> findAll();
}
