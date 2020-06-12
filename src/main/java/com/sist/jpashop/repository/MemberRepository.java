package com.sist.jpashop.repository;

import com.sist.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;


    @Repository
    public class MemberRepository{

        @PersistenceContext //EntityManager를 자동으로 주입해주는 애노테이션
        private EntityManager em;

        @PersistenceUnit//혹시 factory가 필요한 경우
        private EntityManagerFactory emf;

        public void save(Member member){
            em.persist(member);
        }

        public Member findOne(Long id){
            return em.find(Member.class,id);
        }

        public List<Member> findAll(){
           return em.createQuery("select m from Member m",Member.class)
                    .getResultList();
        }

        public List<Member> findByName(String name){
            return em.createQuery("select m from Member m where m.name=:name",Member.class)//아래줄네임~~3
                    .setParameter("name",name)//(맨윗줄인자 네임이 벨류값으로 들어감...)
                    .getResultList();
        }

    }

