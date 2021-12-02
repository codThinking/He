package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{


    private BookService bookService = new BookServiceImpl();

    /**
     * ajax添加商品到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtil.parseInt(req.getParameter("id"),0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项  采用Session实现
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItems(cartItem);
        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("lastName",cartItem.getName());
        resultMap.put("totalNumber",cart.getTotalCount());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * 向购物车添加物品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtil.parseInt(req.getParameter("id"),0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项  采用Session实现
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItems(cartItem);
        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        // 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 向购物车删除物品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1请求参数，获取商品编号
        int id = WebUtil.parseInt(req.getParameter("id"),0);
//        2、获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            //删除商品项
            cart.deleteItem(id);
//            重定向
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ajax更新购物车物品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxUpdateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      获取请求参数id、count
        int id= WebUtil.parseInt(req.getParameter("id"),0);
        int count = WebUtil.parseInt(req.getParameter("count"),1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//      书的库存
        int bookStack = bookService.queryById(id).getStock();
        //如果库存大于修改的数量、按正常流程走
        if (bookStack >= count && cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }else {
            //提示数据有问题重新改   采用ajax
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","超出库存，数据异常！");
            cart.updateCount(id,1);

            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            resp.getWriter().write(json);
//            resp.sendRedirect(req.getHeader("Referer"));
//            req.getSession().setAttribute("msg", new String("超出库存数量异常"));
//            req.setAttribute("outOfStack","超出库存数量异常");
//            //因为使用的是重定向
//            req.setAttribute("count","1");
//            无法使用重定向将以上数据返回前端
//            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 更新物品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      获取请求参数id、count
        int id= WebUtil.parseInt(req.getParameter("id"),0);
        int count = WebUtil.parseInt(req.getParameter("count"),1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//      书的库存
        int bookStack = bookService.queryById(id).getStock();
        if (bookStack >= count && cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }else {
//            req.getSession().setAttribute("msg", new String("超出库存数量异常"));
//            req.setAttribute("outOfStack","超出库存数量异常");
//            //因为使用的是重定向
//            req.setAttribute("count","1");
//            无法使用重定向将以上数据返回前端
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
