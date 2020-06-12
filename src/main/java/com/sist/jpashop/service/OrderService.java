package com.sist.jpashop.service;

import com.sist.jpashop.domain.*;
import com.sist.jpashop.domain.item.Item;
import com.sist.jpashop.repository.ItemRepository;
import com.sist.jpashop.repository.MemberRepository;
import com.sist.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {//비지니스 로직이 들어가는 부분. 회원가입~ 주문~ 그런거!!!!!

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;


    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //회원 엔티티 조회

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);//클래스이름.멤버메서드-> 스태틱ㄴ메서드

        //주문 내용을 저장.
        orderRepository.save(order);

        return order.getId();
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }

    //전체 리스트 받기
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    //검색

}
