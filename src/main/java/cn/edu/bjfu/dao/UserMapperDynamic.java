package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public interface UserMapperDynamic {

    /**
     * 携带了哪个字段查询条件就带上这个字段的值
     *
     * @param user user
     * @return user
     */
    List<User> getUsersByIf(User user);

    /**
     * 解决where最后一个为空多出and报错
     *
     * @param user user
     * @return users
     */
    List<User> getUsersTrim(User user);

    /**
     * switch-case
     *
     * @param user user
     * @return user
     */
    List<User> getUserByConditionChoose(User user);

    /**
     * update
     * @param user user
     */
    void updateUser(User user);

    /**
     * 查询list中id的user
     * @param ids id的List
     * @return List
     */
    List<User> getUsersByConditionForeach(List<Integer> ids);

    /**
     * 批量插入
     * @param users 插入users
     * @return 数量
     */
    Long addUsers(@Param("users")List<User> users);
}