package com.beishan.test;

import com.beishan.dao.IUserDao;
import com.beishan.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SecondLevelCatchTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testFindById() {

        User user1 = userDao.findById(62);
        System.out.println(user1);
        sqlSession.close();

        SqlSession sqlSession2 = factory.openSession();
        userDao = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao.findById(62);

        System.out.println(user1 == user2);

        sqlSession2.close();

    }

    /**
     * 初始化
     */
    @Before
    public void init() throws IOException {
//        1、获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2、根据字节输入流构建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
//        3、根据SqlSessionFactory生产一个SqlSession对象
        sqlSession = factory.openSession(true);
//        4、使用SqlSession获取Dao代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After
    public void release() throws IOException {
        in.close();
    }
}
