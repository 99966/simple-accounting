package com.wuisee.hotel.enums;

import lombok.Getter;

@Getter
public enum RoomStatusEnum {

    EMPTY(0, "空"),
    FULL(1, "满")
    ;

    private Integer code;

    private String message;

    RoomStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
