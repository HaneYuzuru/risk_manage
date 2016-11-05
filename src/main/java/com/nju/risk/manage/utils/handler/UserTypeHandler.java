package com.nju.risk.manage.utils.handler;

import com.nju.risk.manage.domain.domainEnum.UserTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public class UserTypeHandler extends BaseTypeHandler<UserTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserTypeEnum userType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, userType.value());
    }

    @Override
    public UserTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return UserTypeEnum.DEVELOPER;
        }
        int value = resultSet.getInt(s);
        return locateStatus(value);
    }

    @Override
    public UserTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return UserTypeEnum.DEVELOPER;
        }
        int value = resultSet.getInt(i);
        return locateStatus(value);
    }

    @Override
    public UserTypeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return UserTypeEnum.DEVELOPER;
        }
        int value = callableStatement.getInt(i);
        return locateStatus(value);
    }

    private UserTypeEnum locateStatus(int value) {
        for (UserTypeEnum userType : UserTypeEnum.values()) {
            if (userType.value() == value) {
                return userType;
            }
        }
        return UserTypeEnum.DEVELOPER;
    }
}