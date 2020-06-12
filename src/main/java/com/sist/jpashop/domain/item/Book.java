package com.sist.jpashop.domain.item;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter@Getter
@DiscriminatorValue("B")
public class Book extends Item{

    private String author;
    private String isbn;

}
