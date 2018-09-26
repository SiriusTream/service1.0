package com.cntest.su.boot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cntest.su.boot.admin.bean.User;
import com.cntest.su.boot.admin.dao.UserDao;



@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
    @RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "lin";
	}
    
    
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
  	@ResponseBody
  	public String getUser() {
  		return this.userDao.getUserById(1).getName();
  	}
    
}
