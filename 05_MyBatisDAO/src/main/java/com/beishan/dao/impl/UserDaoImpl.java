package com.beishan.dao.impl;

import com.beishan.dao.IUserDao;
import com.beishan.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> selectAll() {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现查询列表
        List<User> users = sqlSession.selectList("com.beishan.dao.IUserDao.selectAll");
//        4、释放资源
        sqlSession.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        sqlSession.insert("com.beishan.dao.IUserDao.saveUser", user);
//        3、提交事务
        sqlSession.commit();
//        4、释放资源
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        sqlSession.insert("com.beishan.dao.IUserDao.updateUser", user);
//        3、提交事务
        sqlSession.commit();
//        4、释放资源
        sqlSession.close();
    }

    @Override
    public void deleteUser(Integer userId) {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        sqlSession.insert("com.beishan.dao.IUserDao.deleteUser", userId);
//        3、提交事务
        sqlSession.commit();
//        4、释放资源
        sqlSession.close();
    }

    @Override
    public User findById(Integer id) {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        User user = sqlSession.selectOne("com.beishan.dao.IUserDao.findById", id);
//        4、释放资源
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findByName(String userName) {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        List<User> users = sqlSession.selectList("com.beishan.dao.IUserDao.findByName", userName);
//        4、释放资源
        sqlSession.close();
        return users;
    }

    @Override
    public Integer findTotal() {
//        1、根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
//        2、调用SqlSession中的方法，实现保存用户操作
        Integer count = sqlSession.selectOne("com.beishan.dao.IUserDao.findTotal");
//        4、释放资源
        sqlSession.close();
        return count;
    }
}
