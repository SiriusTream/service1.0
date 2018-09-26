package com.cntest.su.boot.admin.dao;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cntest.su.boot.admin.bean.User;
import com.cntest.su.boot.admin.mapper.UserMapper;
import com.cntest.su.mybatis.dao.BaseDao;

/**
 * ${DESCRIPTION}
 *
 * @author  
 * @create 2017-06-08 16:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDao extends BaseDao<UserMapper,User> {

    
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    public User getUserById(int id){
        return mapper.selectByPrimaryKey(id);
    }


}
