package com.nju.risk.manage.utils.handler;

import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
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
public class RiskStatusHandler extends BaseTypeHandler<RiskStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, RiskStatusEnum riskStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, riskStatus.value());
    }

    @Override
    public RiskStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return RiskStatusEnum.RISK;
        }
        int value = resultSet.getInt(s);
        return locateStatus(value);
    }

    @Override
    public RiskStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return RiskStatusEnum.RISK;
        }
        int value = resultSet.getInt(i);
        return locateStatus(value);
    }

    @Override
    public RiskStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return RiskStatusEnum.RISK;
        }
        int value = callableStatement.getInt(i);
        return locateStatus(value);
    }

    private RiskStatusEnum locateStatus(int value) {
        for (RiskStatusEnum riskStatus : RiskStatusEnum.values()) {
            if (riskStatus.value() == value) {
                return riskStatus;
            }
        }
        return RiskStatusEnum.RISK;
    }
}