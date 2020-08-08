package com.beishan.dao;

import com.beishan.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户，同时获取到用户的所有账户信息
     * @return
     */
    List<User> selectAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Integer id);
}
