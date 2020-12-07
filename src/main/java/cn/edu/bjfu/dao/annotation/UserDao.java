package cn.edu.bjfu.dao.annotation;

import cn.edu.bjfu.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/7
 */
public interface UserDao {

    /**
     * 查询所有user
     * @return 所有user的链表
     */
    @Select("select * from user")
    List<User> findAll();
}
