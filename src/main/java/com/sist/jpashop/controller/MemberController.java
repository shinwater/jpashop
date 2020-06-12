package com.sist.jpashop.controller;

import com.sist.jpashop.domain.Address;
import com.sist.jpashop.domain.Member;
import com.sist.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/new")
    public String createform(Model model){

        //new MemberForm(): 비어있는 껍데기 - 데이터가 없ㄴ다
        model.addAttribute("memberForm",new MemberForm());//객체를 생성하면서 생성자로 넘겨줌 ->안에 데이터없음

        return "members/createMemberForm";

    }
    @PostMapping("/members/news")
    public String create(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){//에러가있는 경우는 걸러서 다시 회언가입폼창으로 넘어감
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());
        
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        
        //실제 DB에 저장
        memberService.join(member);
        
        return "redirect:/";//main페이지로 다시 넘어감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("list",members);
        return "members/memberList";
    }
}
