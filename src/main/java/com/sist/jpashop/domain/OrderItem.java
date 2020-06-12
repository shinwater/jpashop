package com.sist.jpashop.domain;

import com.sist.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
@Table(name="order_item")
public class OrderItem {
    @Id@GeneratedValue
    @Column(name="order_item_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문상품객체

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order; //주문 객체

    private int orderPrice; //주문 가격쓰
    private int count; //주문 수량량



    //주문 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //재고에서 주문한 수량만큼 뺴준다!
        item.removeStock(count);

        return orderItem;
    }

    //재고 수량 원 상태로 복원. (취소했을때)
    public void cancel() {
        getItem().addStock(count);
    }

    //
    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }
}