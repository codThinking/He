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
        orderDao.saveOrder(new Order("004",new Date(),new BigDecimal(1),0,3));
        orderDao.saveOrder(new Order("005",new Date(),new BigDecimal(1),0,3));
        orderDao.saveOrder(new Order("006",new Date(),new BigDecimal(1),0,3));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void queryForTotalCount(){
        Integer integer = orderDao.queryForTotalCount(2);
        System.out.println(integer);
    }

    @Test
    public void queryForItems(){
        List<Order> orders = orderDao.queryForItems(0, 5,2);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("001",1);
//        System.out.println();
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = orderDao.queryOrderByUserId(3);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}