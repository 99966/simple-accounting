package com.wuisee.hotel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuisee.hotel.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private Integer id;

    /** 订单id. */
    private String orderId;

    /** 房间id. */
    private String roomId;

    /** 房间名字. */
    private String roomName;


    /** 入住天数. */
    private Integer intoDays;

    /** 真是价格. */
    @JsonProperty("price")
    private BigDecimal realPrice;

    /** 订单状态 默认为新下单. */
    private Integer orderStatus;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 支付状态 默认 0 未支付. */
    private Integer payStatus;

    /** 支付方式 默认 0 未支付. */
    private Integer payType;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
