package com.bin.lg.sqlsession;

/**
 * SqlSessionFactory
 *
 * @author Administrator
 * @date 2021/11/19 16:14
 */
public interface SqlSessionFactory {
    SqlSession openSqlSession();
}
