package dao;

import pojo.Order;
import pojo.OrderItem;

import java.util.List;


public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     */
    void saveOrderItem(OrderItem orderItem);

    /**
     * 根据orderId查找OrderItem
     * @param OrderId
     * @return
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}

