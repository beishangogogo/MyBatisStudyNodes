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
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private SqlSessionFactory factory;

    /**
     * 初始化操作
     * @throws IOException
     */
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
//        1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2、获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
//        3、获取SqlSession对象
        sqlSession= factory.openSession(true);
//        4、获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     */
    @After //用于在测试方法执行之后执行
    public void destroy() throws IOException {
//        sqlSession.commit();
        sqlSession.close();
        in.close();

    }

    @Test
    public void testFirstLevelCache() {
        User user1 = userDao.findById(60);
        System.out.println(user1);

//        sqlSession.close();
//        sqlSession = factory.openSession(true);
    sqlSession.clearCache();

        userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findById(60);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testClearCache() {
        User user1 = userDao.findById(60);
        System.out.println(user1);

        user1.setUsername("update clear cache");
        user1.setAddress("beijing");
        userDao.updateUser(user1);
//        sqlSession.commit();

        User user2 = userDao.findById(60);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
