package com.cntest.su.boot.admin.mapper;
 
import org.apache.ibatis.annotations.Param;

import com.cntest.su.boot.admin.bean.User;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);
}