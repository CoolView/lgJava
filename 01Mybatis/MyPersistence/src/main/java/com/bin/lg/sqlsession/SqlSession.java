package com.bin.lg.sqlsession;

import java.util.List;

/**
 * @author bin72
 */
public interface SqlSession {
    /**
     * 查询所有
     *
     * @param statementId namespace + "." + MappedStatement.id
     * @param params      查询参数
     * @return 集合列表
     */
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 根据条件查询单个
     *
     * @param statementId namespace + "." + MappedStatement.id
     * @param params      查询参数
     * @return 结果实体
     */
    <T> T selectOne(String statementId, Object... params) throws Exception;


    /**
     * 为Dao接口生成代理实现类
     *
     * @param mapperClass
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);

}
