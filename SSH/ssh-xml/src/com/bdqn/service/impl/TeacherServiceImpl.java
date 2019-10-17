package com.bdqn.service.impl;

import com.bdqn.dao.TeacherDao;
import com.bdqn.entity.Teacher;
import com.bdqn.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	
	//注入teacherDao
	private TeacherDao teacherDao;
	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}


	@Override
	public Teacher login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		//调用查询用户信息的方法
		Teacher teacher = teacherDao.findTeacherByName(username);
		//如果teacher对象不为空，表示存在该用户
		if(teacher!=null) {
			//比较数据库中的密码与用户输入的密码是否一致，一致表示登录成功
			if(teacher.getPassword().equals(password)) {
				//表示登录成功
				return teacher;
			}
		}
		//登录失败（用户不存在，密码错误）
		return null;
	}

}
