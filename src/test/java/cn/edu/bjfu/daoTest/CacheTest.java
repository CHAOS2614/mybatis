package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserDao;
import cn.edu.bjfu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Chao Huaiyu
 * @date 2020/12/9
 */
public class CacheTest {

    @Test
    public void firstLevelCache(){
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserDao userMapper = session.getMapper(UserDao.class);
            User user = userMapper.getUserById(63);
            System.out.println(user);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(userMapper.getUserById(63));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
