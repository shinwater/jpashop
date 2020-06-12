package com.sist.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter@Getter

//테이블이름을 다른걸로 바꿔주기위해
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;


    @ManyToOne(fetch= FetchType.LAZY)//뒤가 ㅇOne이면 패치타입을 LAZY(지연로딩)로 바꿔주셔야해욤 ㅎㅎ EAGER로 하면 느려진댕
    @JoinColumn(name="member_id")
    private Member member; //주문한 회운ㅓ 객체

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery; //배송정보객체
    private LocalDateTime orderDate; //주문시간
    @Enumerated(EnumType.STRING) //열거형 오디널: 기존값들이 안바뀜 ㅎㅎ
    private OrderStatus status;     //주문상태. [ORDER,CANCEL]

    //핵심적으로 중요한 멤버가 있는 곳에 설정하면 좋은 메서드 : 연관관계 편의 메서드.
    //양쪽에 적용해야 하는 경우에 사용하면 좋음.
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);//?? 현재주문한내역이 리스트에 저장됨.
    }


    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //주문생성메서드

    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems) {
        //주문한 내역이 여러개가 들어갈수있는 경우 -> 보통은 리스트로 표현하나 "..."으로 나타낼수도있음~~~

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        //orderItems ->여러개!
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }


    //주문취소메서드
    public void cancel() {
        if(delivery.getStatus().equals(DeliveryStatus.COMP)){
            //배송완료 된 경우
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니다.");
        }

        //주문이 취소됨.
        this.setStatus(OrderStatus.CANCEL);

        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }
    }

    //전체 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;

        for (OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }


}
