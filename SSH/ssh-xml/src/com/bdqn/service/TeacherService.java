package com.bdqn.service;

import com.bdqn.entity.Teacher;

public interface TeacherService {

	/**
	 * ��ʦ��¼�ķ���
	 * @param username	�û���
	 * @param password	����
	 * @return
	 * @throws Exception
	 */
	Teacher login(String username,String password) throws Exception;
}
