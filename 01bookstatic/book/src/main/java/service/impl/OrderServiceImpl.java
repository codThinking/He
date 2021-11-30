package service.impl;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userid) throws RuntimeException{
        //订单号唯一
        String orderId = System.currentTimeMillis()+""+userid;
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userid));
        //遍历购物车中的商品项转换为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            if (book.getStock()>cartItem.getCount()){
                book.setSales(book.getSales()+cartItem.getCount());
                book.setStock(book.getStock()-cartItem.getCount());
            } else {
                throw new RuntimeException("out of stock!");
            }
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrder(Integer userid) {
        List<Order> orders = orderDao.queryOrderByUserId(userid);
        return orders;
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
