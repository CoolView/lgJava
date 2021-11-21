package com.bin.lg.sqlsession;

import com.bin.lg.domain.Configuration;
import com.bin.lg.domain.MappedStatement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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
        Object o = Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass},
                (proxy, method, args) -> {
                    String methodName = method.getName();
                    // namespace 为 className
                    String className = method.getDeclaringClass().getName();
                    String key = className + "." + methodName;
                    Type returnType = method.getGenericReturnType();
                    // 判断是否实现泛型类型参数化，简单区分
                    if (returnType instanceof ParameterizedType) {
                        return selectList(key, args);
                    } else {
                        return selectOne(key, args);
                    }
                }
        );
        return (T) o;
    }
}
