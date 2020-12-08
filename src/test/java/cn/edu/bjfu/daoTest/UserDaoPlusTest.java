package cn.edu.bjfu.daoTest;

import cn.edu.bjfu.dao.UserDaoPlus;
import cn.edu.bjfu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Chao Huaiyu
 * @date 2020/12/8
 */
public class UserDaoPlusTest {

    @Test
    public void getUserAndDept() {
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserDaoPlus mapper = session.getMapper(UserDaoPlus.class);
            User user = mapper.getUserAndDept(56);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserAndDeptByStep() {
        try (SqlSession session = new UserDaoTest().getSession()) {
            UserDaoPlus mapper = session.getMapper(UserDaoPlus.class);
            User user = mapper.getUserAndDeptStep(56);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}