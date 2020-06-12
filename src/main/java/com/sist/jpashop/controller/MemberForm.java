package com.sist.jpashop.controller;

//DTO개념이랑 비슷하댕

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
public class MemberForm {
    @NotEmpty(message="회원이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;


}
