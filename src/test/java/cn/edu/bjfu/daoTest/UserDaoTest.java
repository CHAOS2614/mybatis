package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserDao;
import cn.edu.bjfu.domain.Department;
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
import java.util.Map;

/**
 * @author Chao Huaiyu
 * @date 2020/12/7
 */
public class UserDaoTest {

    private String xmlName = "sqlmapconfig.xml";


    @Test
    public void getUserById(){
        try (SqlSession session = getSession()) {
            UserDao mapper = session.getMapper(UserDao.class);
            User user = mapper.getUserById(56);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void findByIdAndUsername() {
        try (SqlSession session = getSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            User user = userDao.findByIdAndUsername(58, "index");
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByAddress() {
        try (SqlSession session = getSession()) {
            UserDao mapper = session.getMapper(UserDao.class);
            Map<Integer, User> users = mapper.getUserByAddress("%北京%");
            System.out.println(users);
            users.entrySet().stream()
                    .filter(u -> u.getValue().getAddress().contains("密云"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser() {
        try (SqlSession session = getSession()) {
            cn.edu.bjfu.dao.UserDao userDao = session.getMapper(cn.edu.bjfu.dao.UserDao.class);
            User user = new User(null, "chaos", new Date(), "男", "北京密云",new Department());
            userDao.addUser(user);
            session.commit();
            System.out.println(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        try (SqlSession session = getSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            userDao.updateUser(new User(54, "志志", new Date(), "女", "北京海淀",new Department()));
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser() {
        try (SqlSession session = getSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            for (int i = 40; i < 50; i++) {
                Long count = userDao.deleteUser(i);
                System.out.println(count);
            }
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    SqlSession getSession() throws IOException {
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
