package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;
import utils.WebUtil;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
       bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page page(int pageNo, int pageSize) {
//        总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        int begin = (pageNo-1)*pageSize;
//        当前页数据
        List<Book> pageList = bookDao.queryForItems(begin,pageSize);
//        总页数
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize != 0 ){
            pageTotal += 1;
        }
        Page<Book> page = new Page(pageNo,pageTotal,pageSize,pageTotalCount,pageList);
        return page;


    }
}
