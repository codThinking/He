package test;

import dao.BookDao;
import org.junit.Test;
import pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService =new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"第一本数","无名",new BigDecimal(199),11,12,null));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"第一本数修改","无名",new BigDecimal(199),11,12,null));
    }

    @Test
    public void queryById() {
        System.out.println(bookService.queryById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book: bookService.queryBooks()){
            System.out.println(book);
        }
    }
}