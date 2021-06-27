package com.wineberryhalley.bclassapp.captcha.utils;

/**
 * Created by chensongsong on 2020/8/26.
 */
public enum RiskTypeEnum {

    SLIDE("slide"),
    CLICK("click"),
    FULLPAGE("fullpage"),
    OTHER("other");

    public String type;

    RiskTypeEnum(String type) {
        this.type = type;
    }
}
