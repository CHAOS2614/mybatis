package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.Department;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public interface DepartmentDao {

    /**
     * 根据部门id查询部门
     * @param id 部门id
     * @return 部门
     */
    Department getDepartmentById(Integer id);
}
