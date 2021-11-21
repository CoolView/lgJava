package com.bin.lg.domain;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * sqlSession.xml 文件对象
 *
 * @author Administrator
 * @date 2021/11/19
 */
@Data
public class Configuration {
    /**
     * 数据源
     */
    private DataSource dataSources;
    /**
     * key: statementId （namespace + "." + MappedStatement.id）
     * value: MappedStatement
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();
}
