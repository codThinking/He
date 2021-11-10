package pojo;

import java.util.List;

/**
 * page是分页的模型对象
 * @param <T> 具体的javabean类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
//    当前页码
    public Integer pageNo;
//    总页码
    private Integer pageTotal;
//    当前显示数量
    private Integer pageSize = PAGE_SIZE;
//    总记录数
    private Integer pageTotalCount;
//    当前页数据
    private List<T> items;

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Page(Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Page() {
    }
}
