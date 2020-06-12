package com.sist.jpashop.service;

import com.sist.jpashop.domain.Address;
import com.sist.jpashop.domain.Member;
import com.sist.jpashop.domain.Order;
import com.sist.jpashop.domain.OrderStatus;
import com.sist.jpashop.domain.item.Book;
import com.sist.jpashop.domain.item.Item;
import com.sist.jpashop.repository.OrderRepository;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(false) 저ㅏㅇ상적으로 처리되는지만 확인할거얌
class OrderServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","마포구","222-1123"));//인자생성자가 없어서 오류나네~~Address가서 인자생성자를 만들어야함.
        em.persist(member);

        Item book = new Book();//다형성


        book.setName("어린왕자");
        book.setPrice(15000);
        book.setStockQuantity(10);

        em.persist(book);

        int orderCount = 1;

        //주문완료
        Long orderId = orderService.order(member.getId(),book.getId(),orderCount);

        //검증
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.ORDER,getOrder.getStatus());
        //OrderStatus.ORDER: enum타입의 값 = 현재주문상태의 값 -> 같은지 확인해보자~~~~
        Assertions.assertEquals(1,getOrder.getOrderItems().size());// 주문수량이 일치하는지 확인해보장
        Assertions.assertEquals(15000*orderCount,getOrder.getTotalPrice());//
        Assertions.assertEquals(9,book.getStockQuantity());//재고수량




    }
}