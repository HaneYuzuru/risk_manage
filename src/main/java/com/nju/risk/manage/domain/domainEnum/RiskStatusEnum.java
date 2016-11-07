package com.nju.risk.manage.domain.domainEnum;

/**
 * author: winsky
 * date: 2016/11/5
 * description:风险状态
 */
public enum RiskStatusEnum {
    RISK(0, "风险状态"), PROBLEM(1, "问题状态"), SOLVED(2, "解决状态");

    private int value;
    private String type;

    private RiskStatusEnum(int value, String type) {
        this.type = type;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String type() {
        return type;
    }

    public static RiskStatusEnum fromValue(int value) {
        for (RiskStatusEnum riskStatus : RiskStatusEnum.values()) {
            if (riskStatus.value == value) {
                return riskStatus;
            }
        }
        return null;
    }

    public static RiskStatusEnum fromType(String type) {
        for (RiskStatusEnum riskStatus : RiskStatusEnum.values()) {
            if (riskStatus.type.equals(type)) {
                return riskStatus;
            }
        }
        return null;
    }
}
