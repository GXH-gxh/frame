package cn.smbms.dao;

import java.util.List;

import cn.smbms.entity.User;

public interface UserMapper {

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findUserList();
}
