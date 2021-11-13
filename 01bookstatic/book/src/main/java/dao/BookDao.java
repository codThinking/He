package dao;

import pojo.Book;
import pojo.Page;

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

    /**
     * 查询总记录数
     * @return
     */
    public Integer queryForPageTotalCount();

    /**
     * 查询当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForItems(int begin, int pageSize);

    /**
     * 依据最大最小价格查询总记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 依据最大最小价格查询当前页数据
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
