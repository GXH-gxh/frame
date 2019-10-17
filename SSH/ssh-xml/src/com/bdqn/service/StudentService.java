package com.bdqn.service;

import java.util.List;

import com.bdqn.entity.Student;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

public interface StudentService {
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
}
