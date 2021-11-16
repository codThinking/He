package service.impl;

import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userid) {

        //订单号唯一
        String orderId = System.currentTimeMillis()+""+userid;
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userid));

        //遍历购物车中的商品项转换为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
        }
        cart.clear();
        return orderId;

    }

    @Override
    public List<Order> showAllOrders() {
        return null;
    }

    @Override
    public void sendOrder(String orderId) {

    }

    @Override
    public Order showOrderDetails(String OrderId) {
        return null;
    }

    @Override
    public List<Order> showMyOrder(Integer userid) {
        return null;
    }

    @Override
    public void receiveOrder(String OrderId) {

    }
}
