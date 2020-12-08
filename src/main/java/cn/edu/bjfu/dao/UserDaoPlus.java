package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.User;

import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public interface UserDaoPlus {


    /**
     * 查询user与部门信息，外键约束
     * @param id userId
     * @return user
     */
    User getUserAndDept(Integer id);

    /**
     * 查询user与部门信息，分步查询
     * @param id userId
     * @return user
     */
    User getUserAndDeptStep(Integer id);

    /**
     * 根据部门id查用户
     * @param deptId 部门id
     * @return users
     */
    List<User> getUsersByDeptId(Integer deptId);

}
