package com.wuisee.hotel.dataobject;

import com.wuisee.hotel.enums.OrderStatusEnum;
import com.wuisee.hotel.enums.PayStatusEnum;
import com.wuisee.hotel.enums.PayTypeEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    /** 订单id. */
    private String orderId;

    /** 订单状态 默认为新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单总金额. */
    private String roomId;

    /** 支付状态 默认 0 未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 支付方式 默认 0 未支付. */
    private Integer payType = PayTypeEnum.MONEY.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;
}
