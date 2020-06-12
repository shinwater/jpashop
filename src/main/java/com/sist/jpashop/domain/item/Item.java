package com.sist.jpashop.domain.item;

import com.sist.jpashop.domain.Category;
import com.sist.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //단일테이블에 한꺼번에 때려집어넣는것.
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id@GeneratedValue
    private Long id;
    private String name;        //상품명
    private int price;          //상품의 가격
    private int stockQuantity;  //적정  재고수량




    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();


    //재고수량을 증가시키는 메서드.(쭈문후 취소시)
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고수량 감소 로직(주문시 수량감소)
    public void removeStock(int count) {
        int resStock = (this.stockQuantity -= count);

        if(resStock<0){
            throw new NotEnoughStockException("재고 수량 필요");
        }

        this.stockQuantity = resStock;
    }
}
