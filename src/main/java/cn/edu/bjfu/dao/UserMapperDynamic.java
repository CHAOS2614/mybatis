package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.User;

import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public interface UserMapperDynamic {

    /**
     * 携带了哪个字段查询条件就带上这个字段的值
     * @param user user
     * @return user
     */
    List<User> getUsersByIf(User user);



}
