package com.wuisee.hotel.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id. */
    private String orderId;

    /** 客人名字. */
    private String travelerName;

    /** 客人电话. */
    private String travelerPhone;

    /** 房间id。 */
    private String roomId;

    /** 房间名称. */
    private String roomName;

    /** 房间实际价格. */
    private BigDecimal realPrice;

    /** 入住天数. */
    private Integer intoDays;

    private String bz;

}
