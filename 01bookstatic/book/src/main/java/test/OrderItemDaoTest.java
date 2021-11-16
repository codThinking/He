package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"001",1, new BigDecimal(1),new BigDecimal(2),"12134"));
    }
}