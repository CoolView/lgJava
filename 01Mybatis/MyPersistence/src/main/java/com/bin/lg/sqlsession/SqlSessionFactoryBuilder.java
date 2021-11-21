package com.bin.lg.sqlsession;

import com.bin.lg.config.XMLConfigBuilder;
import com.bin.lg.domain.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * SqlSessionFactoryBuilder
 *
 * @author Administrator
 * @date 2021/11/19 16:11
 */
@Data
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        // 解析配置文件，封装 Configuration
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        // 创建 sqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }
}
