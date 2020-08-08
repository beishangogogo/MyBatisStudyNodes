package com.beishan.dao;

import com.beishan.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll() throws IOException;

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据名称模糊查询用户
     * @param userName
     * @return
     */
    List<User> findByName(String userName);

    /**
     * 获取用户的总记录条数
     * @return
     */
    Integer findTotal();
}
