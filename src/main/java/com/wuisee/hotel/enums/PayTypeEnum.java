package com.wuisee.hotel.enums;

import lombok.Getter;

@Getter
public enum PayTypeEnum {
    ALIPAY(0, "支付宝支付"),
    WECHAT(1, "微信支付"),
    MONEY(2, "现金支付");

    private Integer code;

    private String message;

    PayTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
