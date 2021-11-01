package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
//        1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        System.out.println(code);

//        2、验证验证码是否正确
        if("abcde".equalsIgnoreCase(code)){
            //正确
            //  3、检查用户名是否可以用?
            if(userService.existsUsername(username)){
                //不可用
                System.out.println("用户名输入错误");
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            }else {
                //可用 调用service保存到数据库
                userService.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }
        }else{
            //跳回注册页面
            System.out.println("验证码输入错误！");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
        }

    }
}
