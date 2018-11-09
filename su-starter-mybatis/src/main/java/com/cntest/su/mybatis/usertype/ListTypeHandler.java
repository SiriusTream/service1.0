package com.cntest.su.mybatis.usertype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.cntest.su.utils.StringUtils;

/**
 * List类型转换器。
 */
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListTypeHandler extends BaseTypeHandler<List<String>> {
  @Override
  public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String[] split = rs.getString(columnName).split(",");
    return Arrays.asList(split);
  }

  @Override
  public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String[] split = rs.getString(columnIndex).split(",");
    return Arrays.asList(split);
  }

  @Override
  public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String[] split = cs.getString(columnIndex).split(",");
    return Arrays.asList(split);
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex,
      List<String> parameter, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(columnIndex, StringUtils.join(parameter, ","));
  }
}
