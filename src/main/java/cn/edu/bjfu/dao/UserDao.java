package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.User;

import java.util.List;

/**
 * 用户的
 * @author Chao Huaiyu
 * @date 2020/12/6
 */
public interface UserDao {

    /**
     * 查询所有user
     * @return 所有user的链表
     */
    List<User> findAll();
}
