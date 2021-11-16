package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItems() {
        Cart cart = new Cart();
        cart.addItems(new CartItem(1,"test1",2,new BigDecimal(10)));
        cart.addItems(new CartItem(2,"test2",2,new BigDecimal(10)));
        cart.addItems(new CartItem(3,"test3",2,new BigDecimal(10)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItems(new CartItem(1,"test1",2,new BigDecimal(10)));
        cart.addItems(new CartItem(2,"test2",2,new BigDecimal(10)));
        cart.addItems(new CartItem(3,"test3",2,new BigDecimal(10)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItems(new CartItem(1,"test1",2,new BigDecimal(10)));
        cart.addItems(new CartItem(2,"test2",2,new BigDecimal(10)));
        cart.addItems(new CartItem(3,"test3",2,new BigDecimal(10)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItems(new CartItem(1,"test1",2,new BigDecimal(10)));
        cart.addItems(new CartItem(2,"test2",2,new BigDecimal(10)));
        cart.addItems(new CartItem(3,"test3",2,new BigDecimal(10)));
        cart.updateCount(1,1);
        System.out.println(cart);
    }
}