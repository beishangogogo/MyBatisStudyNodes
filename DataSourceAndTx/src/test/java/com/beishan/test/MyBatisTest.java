package com.beishan.test;

import com.beishan.dao.IUserDao;
import com.beishan.domain.QueryVo;
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

/**
 * 测试mybatis的crud操作
 */
public class MyBatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {

        List<User> users = userDao.selectAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {

        User user = new User();
        user.setUsername("mybatisTest");
        user.setAddress("xxx-xxx-xxx");
        user.setSex("M");
        user.setBirthday(new Date());

        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {

        User user = new User();
        user.setId(44);
        user.setUsername("mybatis_update user");
        user.setAddress("xxx-xxx-xxx");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(59);
    }

    /**
     * 根据id查询用户
     * @return
     */
    @Test
    public void testFindById() {
        User user = userDao.findById(45);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%mybatis%");
        for(User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试使用QueryVo作为测试条件查询用户
     */
    @Test
    public void findUserByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("mybatis%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindTotal() {
        System.out.println(userDao.findTotal());
    }

    /**
     * 初始化操作
     * @throws IOException
     */
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
//        1、读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2、获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
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
        in.close();
        sqlSession.close();
    }
}
