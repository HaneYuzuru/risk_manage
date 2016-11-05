package com.nju.risk.manage.domain.domainEnum;

/**
 * author: winsky
 * date: 2016/11/5
 * description: 风险影响程度
 */
public enum ImpactEnum {
    LOW(0, "低"), MIDDLE(1, "中"), HIGH(2, "高");

    private int value;
    private String type;

    private ImpactEnum(int value, String type) {
        this.type = type;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String type() {
        return type;
    }

    public static ImpactEnum fromValue(int value) {
        for (ImpactEnum impact : ImpactEnum.values()) {
            if (impact.value == value) {
                return impact;
            }
        }
        return null;
    }
}
