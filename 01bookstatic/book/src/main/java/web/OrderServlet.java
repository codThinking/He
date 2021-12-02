package web;

import com.google.gson.Gson;
import pojo.*;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxCreateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String orderId = null;
        try {
            orderId = orderService.createOrder(cart, userid);
            req.getSession().setAttribute("orderId",orderId);
//        请求转发
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }catch (Exception e ){
            e.printStackTrace();
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","所选商品超出库存，请修改后重新提交");

            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            resp.getWriter().write(json);
        }
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

    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String orderId = req.getParameter("orderId");

        //处理请求参数 调用Service
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);

        req.setAttribute("orderItems",orderItems);
//        请求转发
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req,resp);
    }

    /**
     * 用户确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String orderId = req.getParameter("orderId");

        //处理请求参数 调用Service
        orderService.receiveOrder(orderId);
//        重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 管理员发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String orderId = req.getParameter("orderId");
        //处理请求参数 调用Service
        orderService.sendOrder(orderId);
//        重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 管理员订单管理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    //此处留一个小bug，前端未提示用户权限问题！，且管理员权限校验应在index页面做，！包括book和订单的管理
    protected void orderManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int userid = WebUtil.parseInt(req.getParameter("userid"),0);
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");

        //判断是否为管理员，如果是管理员则进入否则，返回（默认1号为管理员其余为用户）
        if(userid == 1){
            //处理请求参数 调用Service
//            List<Order> orders = orderService.showAllOrders();
            //            req.setAttribute("orders",orders);
            Page page = orderService.page(WebUtil.parseInt(pageNo,1),WebUtil.parseInt(pageSize,Page.PAGE_SIZE),userid);
            page.setUrl("manager/orderServlet?action=orderManager&userid="+userid);
            req.setAttribute("page",page);
//        请求转发
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
        }else {
            req.setAttribute("msg","用户权限不够");
//            //采用ajax返回信息
//            Map<String,Object> resultMap = new HashMap<>();
//            resultMap.put("msg","用户权限不够");
//            Gson gson = new Gson();
//            String json = gson.toJson(resultMap);
//            resp.getWriter().write(json);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         1、获取请求参数
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
        int userid = WebUtil.parseInt(req.getParameter("userid"),0);
//        2、调用service查询数据
        Page page = orderService.page(WebUtil.parseInt(pageNo,1),WebUtil.parseInt(pageSize,Page.PAGE_SIZE),userid);
        page.setUrl("manager/orderServlet?action=page&userid="+userid);
//        3、保存图书到Request域中
        req.setAttribute("page",page);
//        4、请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
}
