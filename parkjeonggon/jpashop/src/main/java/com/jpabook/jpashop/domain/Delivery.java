package com.jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
}
