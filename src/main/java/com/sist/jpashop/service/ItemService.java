package com.sist.jpashop.service;


import com.sist.jpashop.domain.item.Book;
import com.sist.jpashop.domain.item.Item;
import com.sist.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item){
        itemRepository.save(item);
    }


    @Transactional
    public void updateItem(Long itemId, Book param){//영속성컨텍스트를 거치지않고 merge하면 위험해서(나머지내용들이 null로 수정된댕) 하는거임.ㅎ.ㅎㅎ 이해ㅡㄹ 할수없다.
        Book findItem = (Book) itemRepository.findOne(itemId);
        findItem.setId(param.getId());
        findItem.setName(param.getName());
        findItem.setPrice(param.getPrice());
        findItem.setStockQuantity(param.getStockQuantity());
        findItem.setAuthor(param.getAuthor());
        findItem.setIsbn(param.getIsbn());


    }


    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    //전체아이템 가져오기
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

}
