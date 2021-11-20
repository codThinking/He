package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(2,"001",1, new BigDecimal(1),new BigDecimal(2),"12134"));
    }

    @Test
    public void queryForOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("16373802071711");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}