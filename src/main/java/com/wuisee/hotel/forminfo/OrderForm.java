package com.wuisee.hotel.forminfo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderForm {

//    intoDays: 3,
//    price: 100,
//    payType: 0,

    private String roomId;

    private BigDecimal price;

    private Integer intoDays;

    private Integer payType;

    private List items;

}
