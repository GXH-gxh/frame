package com.bdqn.action;

import java.util.List;

import com.bdqn.entity.Student;
import com.bdqn.service.StudentService;
import com.bdqn.vo.StudentQueryVO;

public class StudentAction extends BaseAction{
	
	/*************封装页面参数开始********************/
	//表达式封装
	private StudentQueryVO stuQueryVO;
	/*************封装页面参数结束********************/
	
	//学生列表集合，存在值栈中，可直接使用
	private List<Student> studentList;
	
	
	//注入studentService
	private StudentService studentService;
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}



	/**
	 * 查询学生列表的方法
	 * @return
	 */
	public String findStudentList() {
		try {
			//调用查询总数量的方法
			int totalCount = studentService.getTotalCount(stuQueryVO);
			//计算分页
			page.setTotalCount(totalCount);
			//调用查询学生列表的方法
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
