package com.bin.lg.sqlsession;

import com.bin.lg.domain.Configuration;
import com.bin.lg.domain.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 *
 * @author bin72
 * @date 2021/11/20 11:45
 */
public interface Executor {
    /**
     * 查询方法
     * @param configuration sqlSession 配置
     * @param mappedStatement mapper 配置
     * @param params 参数
     * @return 查询结果
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
