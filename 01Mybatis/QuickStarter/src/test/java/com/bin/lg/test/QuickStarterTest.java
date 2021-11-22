package com.bin.lg.test;

import com.bin.lg.domain.User;
import com.bin.lg.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试类
 *
 * @author Administrator
 * @date 2021/11/22 14:48
 */
public class QuickStarterTest {

    /**
     * 根据 statement key 查询
     */
    @Test
    public void test() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执⾏sql语句
        List<User> userList = sqlSession.selectList("com.bin.lg.mapper.IUserMapper.findAll");
        System.out.println(userList);
        // 释放资源
        sqlSession.close();
    }

    /**
     * 使用代理开发模式
     */
    @Test
    public void test2() throws IOException {
        // 加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获得sqlSession⼯⼚对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            List<User> userList = mapper.findAll();
            System.out.println(userList);
        }
    }
}
