package com.wuisee.hotel.exception;

import com.wuisee.hotel.enums.ResultEnum;

public class HotelException extends RuntimeException {

    private Integer code;

    public HotelException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
