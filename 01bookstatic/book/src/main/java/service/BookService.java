package service;

import pojo.Book;
import pojo.Page;

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

    /**
     * 分页
     * @param parseInt
     * @param parseInt1
     * @return
     */
    public Page page(int parseInt, int parseInt1);

    /**
     * 依据价格分页
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    Page pageByPrice(int pageNo, int pageSize, int min, int max);
}
