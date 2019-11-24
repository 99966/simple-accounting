package com.wuisee.hotel.dataobject;

import com.wuisee.hotel.enums.RoomStatusEnum;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class RoomInfo {

    @Id
    private String roomId;

    /** 房间名称. */
    private String roomName;

    /** 房间默认价格. */
    private BigDecimal roomPrice;

    /**  房间状态.*/
    private Integer roomStatus= RoomStatusEnum.EMPTY.getCode();

    /**  房间描述.*/
    private String roomDescription;

    /**  房间类型(标间，单间，三人间).*/
    private Integer categoryType;
}
