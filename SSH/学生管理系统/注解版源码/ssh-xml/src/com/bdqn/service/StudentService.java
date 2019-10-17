package com.bdqn.service;

import java.util.List;

import com.bdqn.entity.Student;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

public interface StudentService {
	
	public static final String SERVICE_NAME = "com.bdqn.service.impl.StudentServiceImpl";
	/**
	 * ͳ���������ķ���
	 * @param student
	 * @return
	 * @throws Exception
	 */
	int getTotalCount(StudentQueryVO student) throws Exception;
	
	/**
	 * ��ѯѧ���б�
	 * @param student
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Student> findStudentList(StudentQueryVO student,PageBean page) throws Exception;
	
	/**
	 * ���ѧ��
	 * @param student
	 * @return
	 * @throws Exception
	 */
	boolean addStudent(Student student) throws Exception;
	
	/**
	 * ����ѧ����Ų�ѯѧ����Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Student findStudentById(int id) throws Exception;
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param student
	 * @return
	 * @throws Exception
	 */
	boolean updateStudent(Student student) throws Exception;
}
