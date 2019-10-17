package com.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdqn.dao.StudentDao;
import com.bdqn.entity.Student;
import com.bdqn.service.StudentService;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

@Service(StudentService.SERVICE_NAME)
@Transactional
public class StudentServiceImpl implements StudentService{
	
	//×¢ÈëstudentDao
	@Resource
	private StudentDao studentDao;


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

	@Override
	@Transactional(readOnly=false)
	public boolean addStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		try {
			studentDao.addStudent(student);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Student findStudentById(int id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findStudentById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public boolean updateStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		try {
			studentDao.updateStudent(student);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
