package web;

import pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    //创建service对象
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求参数 封装成Book对象
        Book book = WebUtil.copyParamToBean(req.getParameterMap(),new Book());
//        2、调用BookService保存Book
        bookService.addBook(book);
//        3、跳转图书列表页面（重定向）
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求对象，封装成book对象
        Book book = WebUtil.copyParamToBean(req.getParameterMap(), new Book());
//        2、调用BOOkService修改图书
        bookService.updateBook(book);
//        3、重定向图书管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");
    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         1、获取请求参数
        String id = req.getParameter("id");
//        2、调用service查询数据
        Book book = bookService.queryById(WebUtil.parseInt(id,0));
//        3、保存图书到Request域中
        req.setAttribute("book",book);
//        4、请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求参数id
        String id = req.getParameter("id");
//        2、调用BookService删除Book
        bookService.deleteBook(WebUtil.parseInt(id,0));
//        3、重定向图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、查询全部图书
        List<Book> bookList = bookService.queryBooks();

//        2、保存到Request域中
        req.setAttribute("books",bookList);
//        3、请求转发到pages/manager/manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
