package test;

import dao.BookDao;
import dao.impl.BaseDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"第一本数","无名",new BigDecimal(199),11,12,null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"第一本数修改","无名",new BigDecimal(199),11,12,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        int number = bookDao.queryForPageTotalCount();
        System.out.println(number);
    }

    @Test
    public void queryForItems(){
        List<Book> bookList = bookDao.queryForItems(0,5);
        for(Book book:bookList){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        int number = bookDao.queryForPageTotalCountByPrice(1,100);
        System.out.println(number);
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> bookList = bookDao.queryForPageItemsByPrice(1,4,1,100);
        for(Book book:bookList){
            System.out.println(book);
        }

    }
}