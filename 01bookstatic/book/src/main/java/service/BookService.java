package service;

import pojo.Book;

import java.util.List;

public interface BookService {

    /**
     * 添加
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除
     * @param id
     */
    public void deleteBook(Integer id);

    /**
     * 修改
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 查询单个
     * @param id
     * @return
     */
    public Book queryById(Integer id);

    /**
     * 查询全部
     * @return
     */
    public List<Book> queryBooks();

}
