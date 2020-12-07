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
import java.util.Date;
import java.util.List;

/**
 * @author Chao Huaiyu
 * @date 2020/12/7
 */
public class UserDaoTest {

    private String xmlName = "sqlmapconfig.xml";

    @Test
    public void findAll() throws IOException {
        SqlSession sqlSession = getSession();
        //使用SqlSession创建Dao接口的代理对象
        List<User> users;
        if ("sqlmapconfig.xml".equals(xmlName)) {
            System.out.println("使用--->[" + xmlName + "]配置文件，xml配置文件映射方式示例");
            UserDao userDao = sqlSession.getMapper(cn.edu.bjfu.dao.UserDao.class);
            //使用代理对象执行方法
            users = userDao.findAll();
        } else {
            System.out.println("使用--->[" + xmlName + "]配置文件，注解方式示例");
            cn.edu.bjfu.dao.annotation.UserDao userDao = sqlSession.getMapper(cn.edu.bjfu.dao.annotation.UserDao.class);
            //使用代理对象执行方法
            users = userDao.findAll();
        }
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
    }

    @Test
    public void addUser() {
        try (SqlSession session = getSession()) {
            cn.edu.bjfu.dao.UserDao userDao = session.getMapper(cn.edu.bjfu.dao.UserDao.class);
            userDao.addUser(new User(null, "志志", new Date(), "男", "北京海淀"));
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        try (SqlSession session = getSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            userDao.updateUser(new User(54, "志志", new Date(), "女", "北京海淀"));
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser() {
        try (SqlSession session = getSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            for (int i = 40; i <50 ; i++) {
                Long count = userDao.deleteUser(i);
                System.out.println(count);
            }
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private SqlSession getSession() throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(xmlName);
        //创建SqlSessionFactory工厂(构建者模式)
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //使用工厂生产SqlSession创建SQLSession对象(工厂模式)
        //获取的session不会自动提交
        SqlSession session = factory.openSession();
        inputStream.close();
        return session;
    }
}
