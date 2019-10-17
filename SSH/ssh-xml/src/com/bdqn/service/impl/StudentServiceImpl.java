package com.bdqn.service.impl;

import java.util.List;

import com.bdqn.dao.StudentDao;
import com.bdqn.entity.Student;
import com.bdqn.service.StudentService;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

public class StudentServiceImpl implements StudentService{
	
	//×¢ÈëstudentDao
	private StudentDao studentDao;
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public int getTotalCount(StudentQueryVO student) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.getTotalCount(student);
	}

	@Override
	public List<Student> findStudentList(StudentQueryVO student, PageBean page) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentList(student, page);
	}

}
