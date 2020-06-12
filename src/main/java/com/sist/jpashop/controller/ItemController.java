package com.sist.jpashop.controller;

import com.sist.jpashop.domain.item.Book;
import com.sist.jpashop.domain.item.Item;
import com.sist.jpashop.service.ItemService;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.print.DocFlavor;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/item/new")//상품등록 버튼을 누르면 상품등록 Form페이지로 넘어가야함.
    public String createForm(Model model){
        model.addAttribute("itemform", new BookForm());
        return "items/createItemForm";//items 아래에있는 createItemForm.html페이지로 갈거얌

    }

    @PostMapping("/items/news")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.save(book);
        return "redirect:/";  //index페이지로 돌아간돵
    }

    @RequestMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("itemList",items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateForm(@PathVariable("itemId") Long id, Model model){//pathvariable은 위에 있는 변수명이할ㅇ 똑같이 받아줘야함
        Book item = (Book) itemService.findOne(id);
        //isbn이랑 author도 받아오기 위해서 item타입으로 받지않고 book타입으로 받음
        //오른쪽은 item타입이기 떄문에 book타입으로 캐스팅해주기

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("update",form);

        return "items/updateItemForm";

    }

    @PostMapping("/items/{itemId}/editOk")
    public String update(@PathVariable("itemId") Long itemId, @ModelAttribute("update") BookForm form){
        Book book = new Book();


        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.save(book);
        return "redirect:/items";

    }
}
