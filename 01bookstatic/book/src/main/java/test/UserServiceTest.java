package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"bbj168","666666","bbj168@qq.com"));
        userService.registerUser(new User(null,"abc168","666666","bbj168@qq.com"));
    }

    @Test
    public void login() {
        userService.login(new User(null,"bbj168","666666",null));

    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("wzg168")){
            System.out.println("用户名已存在!");
        }else {
            System.out.println("用户名可用！");
        }
    }
}