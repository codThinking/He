package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
//        orderDao.saveOrder(new Order("12134",new Date(),new BigDecimal(1),0,1));
        orderDao.saveOrder(new Order("001",new Date(),new BigDecimal(1),0,1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("12134",2);
        System.out.println();
    }

    @Test
    public void queryOrderByUserId() {
        Order order = orderDao.queryOrderByUserId(1);
        System.out.println(order);
    }
}