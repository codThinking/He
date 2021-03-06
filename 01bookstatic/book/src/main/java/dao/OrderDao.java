package dao;

import pojo.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 查询总记录数
     * @return
     */
    Integer queryForTotalCount(int userid);

    /**
     * 查询当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Order> queryForItems(int begin, int pageSize,int userid);
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryOrders();

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    void changeOrderStatus(String orderId,int status);

    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
    List<Order> queryOrderByUserId(Integer userId);
}
