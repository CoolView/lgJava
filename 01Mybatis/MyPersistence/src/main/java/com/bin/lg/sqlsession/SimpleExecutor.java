package com.bin.lg.sqlsession;

import com.bin.lg.config.BoundSql;
import com.bin.lg.domain.Configuration;
import com.bin.lg.domain.MappedStatement;
import com.bin.lg.utils.GenericTokenParser;
import com.bin.lg.utils.ParameterMapping;
import com.bin.lg.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器简单实现
 *
 * @author bin72
 * @date 2021/11/20 11:47
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSources().getConnection();
        // 2. 获取sql语句：select * from user where id = #{id} and username = #{username}
        //    转换sql语句：select * from user where id = ? and username = ?
        //    转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        // 4. 设置参数，获取到了参数的全路径
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        if (parameterMappingList.size() > 0) {
            Class<?> parameterTypeClass = Class.forName(mappedStatement.getParameterType());
            for (int i = 0; i < parameterMappingList.size(); i++) {
                String content = parameterMappingList.get(i).getContent();
                // 反射
                Field declaredField = parameterTypeClass.getDeclaredField(content);
                // 暴力访问
                declaredField.setAccessible(true);
                // 这里 params 只有一个 User 对象，所以只取第一个
                Object o = declaredField.get(params[0]);
                preparedStatement.setObject(i + 1, o);
            }
        }
        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 6. 返回结果对象
        Class<?> resultTypeClass = Class.forName(mappedStatement.getResultType());
        // 7. 封装返回结果集
        List<E> objects = new ArrayList<>();
        while (resultSet.next()) {
            Object o =  resultTypeClass.newInstance();
            // 元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);
                // 使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(o, value);
                } catch (IntrospectionException e) {
                    // 如数据库字段与实体字段不对应，会报错
                }
            }
            objects.add((E) o);
        }
        return objects;
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     *
     * @param sql sql
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        // #{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        return new BoundSql(parseSql, parameterMappings);
    }
}
