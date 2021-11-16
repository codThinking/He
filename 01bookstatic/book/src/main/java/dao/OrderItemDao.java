package dao;

import pojo.OrderItem;

/**
 * 保存订单项
 */
public interface OrderItemDao {
    void saveOrderItem(OrderItem orderItem);
}
