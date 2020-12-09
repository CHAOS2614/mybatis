package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserMapperDynamic;
import cn.edu.bjfu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public class UserMapperDynamicTest {

    @Test
    public void getUsersByIf() {
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserMapperDynamic mapper = session.getMapper(UserMapperDynamic.class);
            List<User> users = mapper.getUsersByIf(new User(null,"%s%",null,null,null,null));
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}