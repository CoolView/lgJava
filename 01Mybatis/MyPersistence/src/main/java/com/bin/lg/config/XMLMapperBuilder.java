package com.bin.lg.config;

import com.bin.lg.domain.Configuration;
import com.bin.lg.domain.MappedStatement;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * mapper.xml 文件读取
 *
 * @author Administrator
 * @date 2021/11/19 16:16
 */
public class XMLMapperBuilder {
    private final Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parseConfig(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        // 查找所有的 select 节点
        List<Node> selectNodes = rootElement.selectNodes("//select");
        for (Node node : selectNodes) {
            Element element = (Element) node;
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attributeValue("id");
            mappedStatement.setId(id);
            mappedStatement.setParameterType(element.attributeValue("parameterType"));
            mappedStatement.setResultType(element.attributeValue("resultType"));
            mappedStatement.setSql(node.getStringValue());
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
        return configuration;
    }
}
