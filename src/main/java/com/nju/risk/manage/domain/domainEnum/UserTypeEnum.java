package com.nju.risk.manage.domain.domainEnum;

/**
 * author: winsky
 * date: 2016/11/5
 * description:用户类型
 */
public enum UserTypeEnum {
    DEVELOPER(0, "普通工程师"), MANAGER(1, "项目经理");

    private int value;
    private String type;

    private UserTypeEnum(int value, String type) {
        this.type = type;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String type() {
        return type;
    }

    public static UserTypeEnum fromValue(int value) {
        for (UserTypeEnum userType : UserTypeEnum.values()) {
            if (userType.value == value) {
                return userType;
            }
        }
        return null;
    }

    public static UserTypeEnum fromType(String type) {
        for (UserTypeEnum userType : UserTypeEnum.values()) {
            if (userType.type.equals(type)) {
                return userType;
            }
        }
        return null;
    }
}
