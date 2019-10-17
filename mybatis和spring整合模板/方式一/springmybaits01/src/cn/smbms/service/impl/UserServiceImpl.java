package cn.smbms.service.impl;

import java.util.List;

import cn.smbms.dao.UserMapper;
import cn.smbms.entity.User;
import cn.smbms.service.UserService;

public class UserServiceImpl implements UserService{
	
	//×¢ÈëuserMapper
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return userMapper.findUserList();
	}

}
