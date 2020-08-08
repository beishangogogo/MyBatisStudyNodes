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

public class SecondLevelCacheTest {

    private InputStream in;
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
    }

//    /**
//     * 释放资源
//     */
//    @After //用于在测试方法执行之后执行
//    public void destroy() throws IOException {
////        sqlSession.commit();
//        sqlSession.close();
//        in.close();
//
//    }

    @Test
    public void testFirstLevelCache() {
        SqlSession sqlSession1 = factory.openSession();
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(60);
        System.out.println(user1);

        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession();
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(60);
        System.out.println(user1);

        sqlSession2.close();

        System.out.println(user1 == user2);
    }
}
