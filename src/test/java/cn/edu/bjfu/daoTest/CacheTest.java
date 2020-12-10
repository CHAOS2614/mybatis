package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserDao;
import cn.edu.bjfu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chao Huaiyu
 * @date 2020/12/9
 */
public class CacheTest {

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
            二级缓存:（全局缓存）:基于namespace级别的缓存;一个namespace对应一个二级缓存;
                    工作机制:
                        1.一个会话查询一条数据，这个数据就会被保存在当前会话的一级缓存中;
                        2.如果会话关闭，一级缓存中的数据会被保存到二级缓存中，新的会话查询信息，就可以参照二级缓存;
                        3.SQLSession===UserMapper==>User
                                    ===DepartmentMapper==>Department
                          不同namespace查出的数据会放在自己对应的缓存中（map）
                    使用:
                        1.开启全局二级缓存配置
                        2.mapper.xml中配置使用二级缓存:<cache></cache>
                        3.POJO实现序列化接口
            和缓存相关的设置/属性:
                1.cacheEnabled:开启与关闭二级缓存，不影响一级缓存
                2.每个select标签都有useCache="true";
                    false:不使用二级缓存，一级缓存依然适用
                3.每个增删改标签:flushCache="true";一级二级都会清除
                    增删改执行完成后就会清除缓存（一二级都清除）;
                  查询标签:flushCache="false";
                    如果flushCache="true"，每次查询之后都会清空缓存，也就说缓存没有被使用
                4.sqlSession.clearCache();只是清除当前session的一级缓存;
                5.localCacheScope:本地缓存作用域:
                    一级缓存SESSION:当前会话的所有数据保存在会话缓存中;
                    STATEMENT;可以禁用一级缓存
         */

    @Test
    public void secondLevelCache() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlmapconfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession session = factory.openSession();
        User user = session.getMapper(UserDao.class).getUserById(63);
        System.out.println(user);
        session.close();
        session = factory.openSession();
        User userById = session.getMapper((UserDao.class)).getUserById(63);
        System.out.println(userById);
        session.close();
        inputStream.close();
    }

    @Test
    public void firstLevelCache() {
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

    private void getUser() {
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserDao userMapper = session.getMapper(UserDao.class);
            User user = userMapper.getUserById(63);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
