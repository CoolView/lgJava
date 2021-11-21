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
 * TODO
 *
 * @author bin72
 * @date 2021/11/20 23:19
 */
public class PersistenceTest {
    @Test
    public void name() {

    }
    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsStream("sqlSession.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        //调用
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
