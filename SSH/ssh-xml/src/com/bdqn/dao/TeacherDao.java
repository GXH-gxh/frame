package com.bdqn.dao;

import com.bdqn.entity.Teacher;

public interface TeacherDao {

	/**
	 * 	���ݽ�ʦ�û������ҽ�ʦ��Ϣ
	 * @param username ��ʦ�û���
	 * @return
	 * @throws Exception
	 */
	Teacher findTeacherByName(String username) throws Exception;
}
