package com.bdqn.action;

import java.util.List;

import com.bdqn.entity.Student;
import com.bdqn.service.StudentService;
import com.bdqn.vo.StudentQueryVO;

public class StudentAction extends BaseAction{
	
	/*************��װҳ�������ʼ********************/
	//���ʽ��װ
	private StudentQueryVO stuQueryVO;
	/*************��װҳ���������********************/
	
	//ѧ���б��ϣ�����ֵջ�У���ֱ��ʹ��
	private List<Student> studentList;
	
	
	//ע��studentService
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}



	/**
	 * ��ѯѧ���б�ķ���
	 * @return
	 */
	public String findStudentList() {
		try {
			//���ò�ѯ�������ķ���
			int totalCount = studentService.getTotalCount(stuQueryVO);
			//�����ҳ
			page.setTotalCount(totalCount);
			//���ò�ѯѧ���б�ķ���
			studentList = studentService.findStudentList(stuQueryVO, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}



	public StudentQueryVO getStuQueryVO() {
		return stuQueryVO;
	}
	public void setStuQueryVO(StudentQueryVO stuQueryVO) {
		this.stuQueryVO = stuQueryVO;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public StudentService getStudentService() {
		return studentService;
	}

	
}
