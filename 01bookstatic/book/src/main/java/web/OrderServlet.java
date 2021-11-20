package web;

import pojo.Cart;
import pojo.Order;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
//      处理请求参数
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userid = user.getId();
//        调用service
        String orderId = orderService.createOrder(cart, userid);
        req.getSession().setAttribute("orderId",orderId);
//        请求转发
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int userid = WebUtil.parseInt(req.getParameter("userid"),0);

        //处理请求参数 调用Service
        List<Order> orders = orderService.showMyOrder(userid);

        req.setAttribute("orders",orders);
//        请求转发
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }


}
