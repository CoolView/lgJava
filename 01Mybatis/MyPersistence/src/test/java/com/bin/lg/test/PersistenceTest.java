package com.bin.lg.test;

import com.bin.lg.domain.User;
import com.bin.lg.io.Resources;
import com.bin.lg.sqlsession.SqlSession;
import com.bin.lg.sqlsession.SqlSessionFactory;
import com.bin.lg.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 自定义持久层测试
 *
 * @author bin72
 * @date 2021/11/20 23:19
 */
public class PersistenceTest {

    /**
     * 自定义持久层框架 查询测试
     */
    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);

        List<User> users = sqlSession.selectList("user.selectList", user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
