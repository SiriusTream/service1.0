package com.cntest.su.mybatis.usertype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json转换器。
 */
@MappedTypes(Object.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonTypeHandler extends BaseTypeHandler<Object> {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
    try {
      return mapper.readValue(rs.getString(columnName), Object.class);
    } catch (Exception e) {
      throw genSQLException(e);
    }
  }

  @Override
  public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    try {
      return mapper.readValue(rs.getString(columnIndex), Object.class);
    } catch (Exception e) {
      throw genSQLException(e);
    }
  }

  @Override
  public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    try {
      return mapper.readValue(cs.getString(columnIndex), Object.class);
    } catch (Exception e) {
      throw genSQLException(e);
    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex,
      Object parameter, JdbcType jdbcType) throws SQLException {
    try {
      preparedStatement.setString(columnIndex, mapper.writeValueAsString(parameter));
    } catch (Exception e) {
      throw genSQLException(e);
    }
  }

  private SQLException genSQLException(Exception e) {
    return new SQLException("转换目标对象为Json时发生异常。", e);
  }
}
