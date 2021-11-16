package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }

    @Test
    public void showAllOrders() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetails() {
    }

    @Test
    public void showMyOrder() {
    }

    @Test
    public void receiveOrder() {
    }
}