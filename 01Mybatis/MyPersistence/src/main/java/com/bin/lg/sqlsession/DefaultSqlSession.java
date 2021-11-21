package com.bin.lg.sqlsession;

import com.bin.lg.domain.Configuration;
import com.bin.lg.domain.MappedStatement;

import java.util.List;

/**
 * @author bin72
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private final Executor simpleExecutor = new SimpleExecutor();

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return simpleExecutor.query(configuration, mappedStatement, params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<T> list = selectList(statementId, params);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        return null;
    }
}
