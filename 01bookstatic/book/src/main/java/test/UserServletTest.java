package test;

import java.lang.reflect.Method;

//反射测试
public class UserServletTest {
    public void login(){
        System.out.println("login方法调用了");
    }
    public void regist(){
        System.out.println("regist方法调用了");
    }

    public static void main(String[] args) {
        String action = "regist";
        try{
            Method method = UserServletTest.class.getDeclaredMethod(action);
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
