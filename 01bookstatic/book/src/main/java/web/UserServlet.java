package web;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {


    private UserService userService = new UserServiceImpl();

    /**
     * 判断用户名是否重复
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        //调用userSerVice.existUsername
        boolean existsUsername = userService.existsUsername(username);
        //把返回结果封装成map
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        //
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /**
     * 登录servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

//
        User loginUser = userService.login(new User(null, username, password, null));
        if(loginUser == null){
//            把回显信息保存到request域中
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
//页面跳转
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }else {
//            登录成功
//            保存用户登录信息到session
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注册servlet方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
        User user = WebUtil.copyParamToBean(req.getParameterMap(),new User());
//        System.out.println(code);
//        2、验证验证码是否正确
//            1、获取Session中验证码
        String token = (String)  req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//            2、删除Session中验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");

        if(token!=null && token.equalsIgnoreCase(code)){
            //正确
            //  3、检查用户名是否可以用?
            if(userService.existsUsername(user.getUsername())){
                //不可用
                System.out.println("用户名输入错误");
                req.setAttribute("msg","用户名错误");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用 调用service保存到数据库
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //跳回注册页面
            req.setAttribute("msg","验证码输入错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());

//            System.out.println("验证码输入错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、删除Session中用户登录信息
        req.getSession().invalidate();
//        2、重定向到首页
        req.getRequestDispatcher("").forward(req,resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       String action = req.getParameter("action");
//
////       if("login".equals(action)){
////           login(req,resp);
////       }else if("regist".equals(action)){
////           regist(req,resp);
////       }
//        try{
//            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
