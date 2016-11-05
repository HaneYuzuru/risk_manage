package com.nju.risk.manage.utils.handler;

import com.nju.risk.manage.domain.domainEnum.DataStatusEnum;
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
public class DataStatusHandler extends BaseTypeHandler<DataStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DataStatusEnum dataStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, dataStatus.value());
    }

    @Override
    public DataStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.wasNull()) {
            return DataStatusEnum.UNDELETED;
        }
        int value = resultSet.getInt(s);
        return locateStatus(value);
    }

    @Override
    public DataStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.wasNull()) {
            return DataStatusEnum.UNDELETED;
        }
        int value = resultSet.getInt(i);
        return locateStatus(value);
    }

    @Override
    public DataStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.wasNull()) {
            return DataStatusEnum.UNDELETED;
        }
        int value = callableStatement.getInt(i);
        return locateStatus(value);
    }

    private DataStatusEnum locateStatus(int value) {
        for (DataStatusEnum dataStatus : DataStatusEnum.values()) {
            if (dataStatus.value() == value) {
                return dataStatus;
            }
        }
        return DataStatusEnum.UNDELETED;
    }
}