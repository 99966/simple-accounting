package com.wuisee.hotel.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ROOM_NOT_EXIST(10, "房间不存在"),
    ROOM_STETUS_ERROR(11, "房间状态不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
