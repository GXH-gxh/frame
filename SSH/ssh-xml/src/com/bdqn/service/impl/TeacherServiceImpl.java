package com.bdqn.service.impl;

import com.bdqn.dao.TeacherDao;
import com.bdqn.entity.Teacher;
import com.bdqn.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	
	//ע��teacherDao
	private TeacherDao teacherDao;
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}


	@Override
	public Teacher login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		//���ò�ѯ�û���Ϣ�ķ���
		Teacher teacher = teacherDao.findTeacherByName(username);
		//���teacher����Ϊ�գ���ʾ���ڸ��û�
		if(teacher!=null) {
			//�Ƚ����ݿ��е��������û�����������Ƿ�һ�£�һ�±�ʾ��¼�ɹ�
			if(teacher.getPassword().equals(password)) {
				//��ʾ��¼�ɹ�
				return teacher;
			}
		}
		//��¼ʧ�ܣ��û������ڣ��������
		return null;
	}

}
