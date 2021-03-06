package service;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.Page;

import java.util.List;

public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userid
     */
    String createOrder(Cart cart,Integer userid);

    /**
     *查询全部订单
     * @return
     */
    List<Order> showAllOrders();

    /**
     *发货
     * @param orderId
     */
    void sendOrder(String orderId);

    /**
     *查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetails(String orderId);

    /**
     *查看我的订单
     * @param userid
     * @return
     */
    List<Order> showMyOrder(Integer userid);

    /**
     *签收订单确认收货
     * @param orderId
     */
    void receiveOrder(String orderId);

    /**
     * 分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page page(int pageNo, int pageSize, int userid);
}
