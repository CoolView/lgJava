package com.bin.lg.domain;

import lombok.Data;

/**
 * mapper.xml 文件对象
 *
 * @author Administrator
 * @date 2021/11/19 15:38
 */
@Data
public class  MappedStatement {
    /**
     * id
     */
    private String id;
    /**
     * sql 语句
     */
    private String sql;
    /**
     * 输入参数
     */
    private String parameterType;
    /**
     * 返回参数
     */
    private String resultType;

}
