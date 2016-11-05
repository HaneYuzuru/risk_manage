package com.nju.risk.manage.utils.handler;

import com.nju.risk.manage.domain.domainEnum.PossibilityEnum;
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
public class PossibilityHandler extends BaseTypeHandler<PossibilityEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PossibilityEnum possibility, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, possibility.value());
    }

    @Override
    public PossibilityEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return PossibilityEnum.LOW;
        }
        int value = resultSet.getInt(s);
        return locateStatus(value);
    }

    @Override
    public PossibilityEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return PossibilityEnum.LOW;
        }
        int value = resultSet.getInt(i);
        return locateStatus(value);
    }

    @Override
    public PossibilityEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return PossibilityEnum.LOW;
        }
        int value = callableStatement.getInt(i);
        return locateStatus(value);
    }

    private PossibilityEnum locateStatus(int value) {
        for (PossibilityEnum possibility : PossibilityEnum.values()) {
            if (possibility.value() == value) {
                return possibility;
            }
        }
        return PossibilityEnum.LOW;
    }
}