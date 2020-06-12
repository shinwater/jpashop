package com.sist.jpashop.service;

import com.sist.jpashop.domain.Member;
import com.sist.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    //회원가입
    public Long join(Member member){
        valdateDuplicateMember(member);//중복회원검증

        memberRepository.save(member);
        return member.getId();
    }

    private void valdateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            System.out.println("이미 존재하는 회원");
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체회원 조회 메서드
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //특정 ID에 해당하는 객체를 조회하는 메서드
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
