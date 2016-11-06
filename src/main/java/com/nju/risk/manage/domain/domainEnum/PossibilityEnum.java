package com.nju.risk.manage.domain.domainEnum;

/**
 * author: winsky
 * date: 2016/11/5
 * description:风险可能性
 */
public enum PossibilityEnum {
    LOW(0, "低"), MIDDLE(1, "中"), HIGH(2, "高");

    private int value;
    private String type;

    private PossibilityEnum(int value, String type) {
        this.type = type;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String type() {
        return type;
    }

    public static PossibilityEnum fromValue(int value) {
        for (PossibilityEnum possibility : PossibilityEnum.values()) {
            if (possibility.value == value) {
                return possibility;
            }
        }
        return null;
    }

    public static PossibilityEnum fromType(String type) {
        for (PossibilityEnum possibility : PossibilityEnum.values()) {
            if (possibility.type.equals(type)) {
                return possibility;
            }
        }
        return null;
    }
}
