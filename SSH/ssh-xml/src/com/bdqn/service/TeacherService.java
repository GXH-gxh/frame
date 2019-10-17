package com.bdqn.service;

import com.bdqn.entity.Teacher;

public interface TeacherService {

	/**
	 * 教师登录的方法
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 * @throws Exception
	 */
	Teacher login(String username,String password) throws Exception;
}
