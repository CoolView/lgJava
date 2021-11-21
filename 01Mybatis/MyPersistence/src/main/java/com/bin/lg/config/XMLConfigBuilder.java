package com.bin.lg.config;

import com.bin.lg.domain.Configuration;
import com.bin.lg.io.Resources;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sqlSession.xml 文件读取
 *
 * @author Administrator
 * @date 2021/11/19 16:16
 */
public class XMLConfigBuilder {
    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        // 查找所有的 property 节点
        List<Node> dataSourcePropertyNodes = rootElement.selectNodes("//property");
        Map<String, String> map = new HashMap<>();
        for (Node node : dataSourcePropertyNodes) {
            Element element = (Element) node;
            String name = element.attributeValue("name");
            String value = node.getStringValue();
            map.put(name, value);
        }
        // 设置数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(map.get("driverClass"));
        dataSource.setJdbcUrl(map.get("url"));
        dataSource.setUser(map.get("username"));
        dataSource.setPassword(map.get("password"));
        configuration.setDataSources(dataSource);

        // mapper.xml 解析
        List<Node> mapperNodes = rootElement.selectNodes("//mapper");
        for (Node node : mapperNodes) {
            Element element = (Element) node;
            String resource = element.attributeValue("resource");
            InputStream mapperInputStream = Resources.getResourceAsStream(resource);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parseConfig(mapperInputStream);
        }
        return configuration;
    }
}
