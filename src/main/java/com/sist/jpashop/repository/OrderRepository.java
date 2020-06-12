package com.sist.jpashop.repository;

import com.sist.jpashop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext//영속성 컨텍스트? 를 가져와야할때 , 객체를 가져올때는 Autowired가 좋대!
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    public Order findOne(Long id){
        return em.find(Order.class,id);
    }


    public List<Order> findAll() {
        return em.createQuery("select o from Order o",Order.class)
                .getResultList();
    }
}
