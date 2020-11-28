package com.cmw.enums;

/**
 * @autor: cmw
 * @create:
 * @description:  枚举类
 */
public enum MyEnum {

    SUCCESS("0","成功"),
    FAIL("1","失败"),
    UNKNOW("2","状态未知");
    private String value;

    private String name;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    MyEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
