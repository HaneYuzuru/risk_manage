package com.nju.risk.manage.utils;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * author: winsky
 * date: 2016/11/5
 * description:SQL执行监视器
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}), @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SQLExecInterceptor implements Interceptor {
    private static final long EXECUTE_THRESHOLD_TIME = 5L;

    private static final ThreadLocal<DateFormat> dateFormatter = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        long start = System.currentTimeMillis();

        Object returnValue = invocation.proceed();

        long time = (System.currentTimeMillis() - start);
        // 非SELECT操作, 或者操作耗时超过5s则记日志
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType()) || time > EXECUTE_THRESHOLD_TIME) {
            String methodName = mappedStatement.getId();
            String sql = dumpSql(mappedStatement.getConfiguration(), mappedStatement.getBoundSql(parameter));
            // Loggers.SQL.log(methodName, time, sql);
        }

        return returnValue;
    }

    private static String dumpSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (!parameterMappings.isEmpty() && parameterObject != null) {

            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {

                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {

                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));

                    } else if (boundSql.hasAdditionalParameter(propertyName)) {

                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }

        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = "";

        if (obj instanceof String) {
            value = "'" + obj.toString().replaceAll("\\$", " ") + "'";
        } else if (obj instanceof Date) {
            value = "'" + dateFormatter.get().format((Date) obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString().replaceAll("\\$", " ");
            }
        }

        return value;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
