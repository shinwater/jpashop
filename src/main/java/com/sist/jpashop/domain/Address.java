package com.sist.jpashop.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor//인자가 없는 생성자: 기본생성자
@AllArgsConstructor//멤버를 전체인자로 가지는 모든 인자생성자 오오ㅗ오~~ 자동~~오오ㅗ~~~
//주소값을 가지는 객체
//@Embeddable: 애노테이션을 지정한 클래스를 밸류 클래스라고 함.
// 밸류클래스란 int,double 형처럼 하나의 값을 나타낸 클래스를 말함
// 보통 주소 (address)라는 값을 저장하기 위해서 아래와 같이 String 변수에 저장하여 관리함.
public class Address {
/*주소값을 저장하는 객체 : 공통적으로 많이 쓰이는 객체는 묶어줄수있음*/

    private String city;
    private String street;
    private String zipcode;
}
