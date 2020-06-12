package com.sist.jpashop.controller;


import com.sist.jpashop.domain.Member;
import com.sist.jpashop.domain.Order;
import com.sist.jpashop.domain.item.Item;
import com.sist.jpashop.service.ItemService;
import com.sist.jpashop.service.MemberService;
import com.sist.jpashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    /* 얘네는 왜 필요할까 ? : 주문을 할 때 주문자의 정보가 있어야하기 때문에 ㅎㅎㅎㅎㅎ */
    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        //전체회원을 조회해서 가져온다. findMembers(): findAll()으로 멤버객체의 전체 리스트를 가져옴.
        List<Member> members = memberService.findMembers();
        //전체상품 조회. 조회한 전체회원중 한사람이 전체상품중 특정한 상품을 주문한다는듸...
        //findItems(): findAll() 이용 아이템 전체 리스트를 가져옴
        List<Item> items = itemService.findItems();

        model.addAttribute("memberList",members);
        model.addAttribute("itemList",items);

        return "order/orderForm";

    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(@RequestParam("memberId") Long memberId,@RequestParam("itemId") Long itemId,@RequestParam("count") int count){
        //주문
        orderService.order(memberId,itemId,count);

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderList(Model model){
        List<Order> orders = orderService.findAll();

        model.addAttribute("list",orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")//받아온것: 주문Id
    public String cancel(@PathVariable("orderId") Long orderId){//@PathVariable: 경로에 있는값 받아올때 사용
        orderService.cancelOrder(orderId);//주문엔티티 조회후 cancel()메서드 불러옴ㅎㅎ
        return "redirect:/orders";
    }



}
