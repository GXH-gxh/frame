package com.bdqn.dao;

import java.util.List;

import com.bdqn.entity.Student;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

public interface StudentDao {

	
	public static final String SERVICE_NAME = "com.bdqn.dao.impl.StudentDaoImpl";
	
	/**
	 * 统计总数量的方法
	 * @param student
	 * @return
	 * @throws Exception
	 */
	int getTotalCount(StudentQueryVO student) throws Exception;
	
	/**
	 * 查询学生列表
	 * @param student
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Student> findStudentList(StudentQueryVO student,PageBean page) throws Exception;
	
	/**
	 * 添加学生
	 * @param student
	 * @throws Exception
	 */
	void addStudent(Student student) throws Exception;
	
	/**
	 * 根据学生编号查询学生信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Student findStudentById(int id) throws Exception;
	
	/**
	 * 修改学生信息
	 * @param student
	 * @throws Exception
	 */
	void updateStudent(Student student) throws Exception;
}
