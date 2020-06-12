package com.sist.jpashop.service;

import com.sist.jpashop.domain.Member;
import com.sist.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("AAA");

        Long saveId = memberService.join(member);

        //검증증
        Assertions.assertEquals(member,memberRepository.findOne(saveId));
    }
    @Test
    public void 중복회원() throws Exception{
        Member member = new Member();
        member.setName("AAA");

        try {
            memberService.join(member);//예외가 발생해야 함.
        }catch (Exception e){
            return;
        }

        fail("예외가 발생해야 한다.");

    }
}