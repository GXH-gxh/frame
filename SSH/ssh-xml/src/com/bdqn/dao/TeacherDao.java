package com.bdqn.dao;

import com.bdqn.entity.Teacher;

public interface TeacherDao {

	/**
	 * 	根据教师用户名查找教师信息
	 * @param username 教师用户名
	 * @return
	 * @throws Exception
	 */
	Teacher findTeacherByName(String username) throws Exception;
}
