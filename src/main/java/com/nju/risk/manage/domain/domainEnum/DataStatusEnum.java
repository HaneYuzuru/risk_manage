package com.nju.risk.manage.domain.domainEnum;

/**
 * author: winsky
 * date: 2016/11/5
 * description:数据有效性
 */
public enum DataStatusEnum {
    UNDELETED(0, "未删除"), DELETED(1, "已删除");

    private int value;
    private String type;

    private DataStatusEnum(int value, String type) {
        this.type = type;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String type() {
        return type;
    }

    public static DataStatusEnum fromValue(int value) {
        for (DataStatusEnum dataStatus : DataStatusEnum.values()) {
            if (dataStatus.value == value) {
                return dataStatus;
            }
        }
        return null;
    }
}
