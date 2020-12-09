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
    public void firstLevelCache() {
        /*
        两级缓存:
            一级缓存:（本地缓存）SQLSession级别的缓存。一级缓存是一直开启;
                      与数据库同一次回话期间查询到的数据会放在本地缓存中。
                      以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库;

                      一级缓存失效情况（没有使用到当前缓存的情况，效果就是还需要再向数据库发出查询）
                            1.SqlSession不同
                            2.SqlSession相同，查询条件不同
                            3.SqlSession相同，查询之间执行了增删改操作
                            4.手动清除了缓存（session.clearCache();）
            二级缓存:（全局缓存）
         */
        try (SqlSession session = new UserDaoTest().getSession();
             SqlSession session2 = new UserDaoTest().getSession()) {
            UserDao userMapper = session.getMapper(UserDao.class);
            User user = userMapper.getUserById(63);
            System.out.println(user);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user1 = userMapper.getUserById(63);
            System.out.println(user == user1);
            UserDao mapper = session2.getMapper(UserDao.class);
            User user2 = mapper.getUserById(63);
            System.out.println(user2 == user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
