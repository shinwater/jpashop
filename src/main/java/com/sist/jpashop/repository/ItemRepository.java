package com.sist.jpashop.repository;

import com.sist.jpashop.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Item item){
        if(item.getId() ==null){
            em.persist(item);
        }else {//상품이 있는 경우
            em.merge(item); //준영속 상태의 엔티티를 영속 상태로 변경.
        }
    }

    @Transactional
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    //검색된 아이템에 대한 전체리스트를 찾아보자아
    @Transactional
    public List<Item> findAll(){//전체리스트 찾을때만 createQuery메서드 이용
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

}
