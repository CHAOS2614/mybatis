package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.DepartmentDao;
import cn.edu.bjfu.dao.UserMapperDynamic;
import cn.edu.bjfu.domain.Department;
import cn.edu.bjfu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
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
            List<User> users = mapper.getUsersTrim(new User(null,"%s%",null,"%男%",null,null));
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByConditionChoose(){
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserMapperDynamic mapper = session.getMapper(UserMapperDynamic.class);
            List<User> users = mapper.getUserByConditionChoose(new User(null,null,null,null,null,null));
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser(){
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserMapperDynamic mapper = session.getMapper(UserMapperDynamic.class);
            mapper.updateUser(new User(65,"louise",new Date(),"女",null,null));
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsersByConditionForeach(){
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserMapperDynamic mapper = session.getMapper(UserMapperDynamic.class);
            List<Integer> ids = new LinkedList<>();
            ids.add(62);
            ids.add(63);
            ids.add(65);
            List<User> users = mapper.getUsersByConditionForeach(ids);
            users.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUsers(){
        try (SqlSession session = new UserDaoTest().getSession();
             SqlSession sqlSession = new UserDaoTest().getSession()) {
            DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
            Department department = departmentDao.getDepartmentById(1);
            UserMapperDynamic mapper = session.getMapper(UserMapperDynamic.class);
            List<User> users = new LinkedList<>();
            users.add(new User(null,"张勇飞",new Date(),"男","北京丰台",department));
            users.add(new User(null,"黄雨璇",new Date(),"女","北京宣武",department));
            users.add(new User(null,"王康",new Date(),"男","北京石景山",department));
            Long count = mapper.addUsers(users);
            session.commit();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}