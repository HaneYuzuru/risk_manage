package com.nju.risk.manage.utils.handler;

import com.nju.risk.manage.domain.domainEnum.ImpactEnum;
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
public class ImpactHandler extends BaseTypeHandler<ImpactEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ImpactEnum impact, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, impact.value());
    }

    @Override
    public ImpactEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return ImpactEnum.LOW;
        }
        int value = resultSet.getInt(s);
        return locateStatus(value);
    }

    @Override
    public ImpactEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return ImpactEnum.LOW;
        }
        int value = resultSet.getInt(i);
        return locateStatus(value);
    }

    @Override
    public ImpactEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return ImpactEnum.LOW;
        }
        int value = callableStatement.getInt(i);
        return locateStatus(value);
    }

    private ImpactEnum locateStatus(int value) {
        for (ImpactEnum impact : ImpactEnum.values()) {
            if (impact.value() == value) {
                return impact;
            }
        }
        return ImpactEnum.LOW;
    }
}