package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100)));

//        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }

    @Test
    public void showAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        String orderId = "002";
        orderService.sendOrder(orderId);
    }

    @Test
    public void showOrderDetails() {
        List<OrderItem> orderItems = orderService.showOrderDetails("16373802071711");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrder() {
        List<Order> orders = orderService.showMyOrder(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder(new String("002"));
    }
}