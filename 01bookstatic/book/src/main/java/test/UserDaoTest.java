package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
//        System.out.println(userDao.queryUserByUsername("admin"));
        if(userDao.queryUserByUsername("admin1234") == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }

    }

    @Test
    public void saveUser() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin") ==null){
            System.out.println("用户名或密码错误，登录失败");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.saveUser(new User(null,"admin11","123456","wzg168@qq.com")));
    }
}