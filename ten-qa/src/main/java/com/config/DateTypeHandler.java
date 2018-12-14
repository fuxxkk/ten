package com.config;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.context.annotation.Configuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Configuration
@MappedJdbcTypes({JdbcType.TIMESTAMP})
@MappedTypes({Date.class})
public class DateTypeHandler implements TypeHandler<Date> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setDate(i,new java.sql.Date(date.getTime()));
    }

    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s);
    }

    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i);
    }
}
