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
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("--------每个用户的信息--------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testFindById() {

        User user = userDao.findById(62);
        System.out.println(user);
    }

    /**
     * 测试根据name模糊查询用户
     */
    @Test
    public void testFindByName() {

        List<User> users = userDao.findUserByName("mybatis%");
        for (User user : users) {
            System.out.println(user);
        }
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
        sqlSession.close();
        in.close();
    }
}
