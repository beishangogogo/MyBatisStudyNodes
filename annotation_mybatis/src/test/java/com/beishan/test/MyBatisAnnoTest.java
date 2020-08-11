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

public class MyBatisAnnoTest {

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
            System.out.println(user);
        }
    }

    /**
     * 测试保存用户
     */
    @Test
    public void testSaveUser() throws IOException {

        User newUser = new User();
        newUser.setUsername("悟空");
        newUser.setAddress("东土大唐");
        newUser.setSex("男");
        newUser.setBirthday(new Date());

        userDao.saveUser(newUser);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdateUser() {

        User newUser = new User();
        newUser.setId(41);
        newUser.setUsername("奇异博士");
        newUser.setAddress("灯塔之国");
        newUser.setSex("男");
        newUser.setBirthday(new Date());

        userDao.updateUser(newUser);
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser() {

            userDao.deleteUser(63);
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
     * 测试查询总用户数量
     */
    @Test
    public void testFindTotal() {

        int count = userDao.findTotal();
        System.out.println("总用户数量为： " + count);
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
