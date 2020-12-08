package cn.edu.bjfu.domain;

import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public class Department {
    private Integer id;
    private String departmentName;
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", users=" + users +
                '}';
    }
}
