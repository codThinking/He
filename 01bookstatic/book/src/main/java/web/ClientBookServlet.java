package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     *前台分页实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//         1、获取请求参数
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");
//        2、调用service查询数据
        Page page = bookService.page(WebUtil.parseInt(pageNo,1),WebUtil.parseInt(pageSize,Page.PAGE_SIZE));
        page.setUrl("client/bookServlet?action=page");
//        3、保存图书到Request域中
        req.setAttribute("page",page);
//        4、请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 首页价格搜索功能实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//         1、获取请求参数
        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"),0);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        int min = WebUtil.parseInt(req.getParameter("min"),0);
        int max = WebUtil.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
//        2、调用service查询数据
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
//        3、保存图书到Request域中
        req.setAttribute("page",page);
//        4、请求转发到pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
