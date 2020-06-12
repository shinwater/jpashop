package com.sist.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BookForm {

    //상품(Book)과 관련된 공통 속성

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //책과 관련된 개별 속성
    private String author;
    private String isbn;



}
