package com.beishan.mybatis.sqlsession;

public interface SqlSessionFactory {

    /**
     * 用于打开一个session对象
     * @return
     */
    SqlSession openSession();
}
