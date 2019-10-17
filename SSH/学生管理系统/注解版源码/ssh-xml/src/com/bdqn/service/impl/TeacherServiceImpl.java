package com.bdqn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdqn.dao.TeacherDao;
import com.bdqn.entity.Teacher;
import com.bdqn.service.TeacherService;

@Service(TeacherService.SERVICE_NAME)
@Transactional(readOnly=false)
public class TeacherServiceImpl implements TeacherService{
	
	//ע��teacherDao
	@Resource
	private TeacherDao teacherDao;
	


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
