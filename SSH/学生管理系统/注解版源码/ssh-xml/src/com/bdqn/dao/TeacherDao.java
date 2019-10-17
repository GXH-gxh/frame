package com.bdqn.dao;

import com.bdqn.entity.Teacher;

public interface TeacherDao {

	public static final String SERVICE_NAME = "com.bdqn.dao.impl.TeacherDaoImpl";
	
	/**
	 * 	���ݽ�ʦ�û������ҽ�ʦ��Ϣ
	 * @param username ��ʦ�û���
	 * @return
	 * @throws Exception
	 */
	Teacher findTeacherByName(String username) throws Exception;
}
