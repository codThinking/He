package pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
//    key是商品编号信息 ，value是商品信息
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    /**
     *  添加商品功能
     * @param cartItem
     */
    public void addItems(CartItem cartItem){
//        先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());

        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            //更新数量
            item.setCount(item.getCount()+1);
//            更新总价
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * 删除商品功能
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 删除全部商品
     */
    public void clear(){
        items.clear();

    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id ,Integer count){
        CartItem item = items.get(id);
         //添加库存校验
        if (item != null){
            item.setCount(count);
        }

    }

    public Cart() {
    }

    /**
     * 获取商品总数
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * 获取商品总价
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items" + items +
                '}';
    }
}
