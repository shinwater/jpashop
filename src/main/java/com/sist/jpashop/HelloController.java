package com.sist.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //RestController는ㄴ 웹브라우저 창에 직접적으로 hello 문자열 출력 .
// Controller는 리턴값에 ".jsp"가 붙음. 리소시스->템플레이트폴더에 hello.~~에 model 값넘겨줌
// REstController는 그냥 헬로우라는 문자열이 반...ㄴ환? 뭘라...ㅠㅠ 이게뭐야
public class HelloController {
    
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","ccc");
        
        return "hello";
    }
}
