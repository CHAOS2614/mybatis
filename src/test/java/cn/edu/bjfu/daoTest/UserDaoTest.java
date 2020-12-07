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
import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/7
 */
public class UserDaoTest {

    @Test
    public void findAll() throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlmapconfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //使用工厂生产SqlSession创建SQLSession对象
        SqlSession sqlSession = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        inputStream.close();


    }
}
