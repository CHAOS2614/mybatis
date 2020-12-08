package cn.edu.bjfu.dao;

import cn.edu.bjfu.domain.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * 用户的
 * @author Chao Huaiyu
 * @date 2020/12/6
 */
public interface UserDao {


    /**
     * 返回map:Map<Integer, User>:键是这条记录的主键，值是记录封装后的javaBean
     * MapKey告诉mybatis封装这个map的时候使用哪个属性作为map的key
     * @param address 按地址查询
     * @return map<Integer, User>
     */
    @MapKey("id")
    Map<Integer, User> getUserByAddress(String address);

    /**
     * 根据id和username查询
     * @param id id
     * @param username username
     * @return user
     */
    User findByIdAndUsername(@Param("id")Integer id,@Param("username") String username);
    /**
     * 查询所有user
     * @return 所有user的链表
     */
    List<User> findAll();

    /**
     * 添加一个user
     * @param user 要添加的user
     */
    void addUser(User user);

    /**
     * 修改一个user信息
     * @param user 要修改的信息
     */
    void updateUser(User user);

    /**
     * 删除一个user
     * @param id 要删除user的id
     * @return 影响行数
     */
    Long deleteUser(Integer id);
}
