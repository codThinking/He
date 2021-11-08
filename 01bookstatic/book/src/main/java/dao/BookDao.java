package dao;

import pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     * 添加
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteBook(Integer id);

    /**
     * 修改
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 单个查询
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部
     * @return
     */
    public List<Book> queryBooks();

}
