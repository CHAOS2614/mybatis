package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserMapperDynamic;
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
}