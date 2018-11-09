package com.cntest.su.mybatis.usertype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.cntest.su.utils.StringUtils;

/**
 * 数组转换器。
 */
@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ArrayTypeHandler extends BaseTypeHandler<String[]> {
  @Override
  public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return rs.getString(columnName).split(",");
  }

  @Override
  public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getString(columnIndex).split(",");
  }

  @Override
  public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return cs.getString(columnIndex).split(",");
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex,
      String[] parameter, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(columnIndex, StringUtils.join(parameter, ","));
  }
}
