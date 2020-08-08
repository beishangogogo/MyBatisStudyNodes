package com.beishan.dao;

import com.beishan.domain.QueryVo;
import com.beishan.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

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
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件
     * @param user  查询的条件：用户名、性别、地址等
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据qveryvo中
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
