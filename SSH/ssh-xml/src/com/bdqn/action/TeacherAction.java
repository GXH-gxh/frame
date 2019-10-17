package com.bdqn.action;

import com.bdqn.entity.Teacher;
import com.bdqn.service.TeacherService;
import com.opensymphony.xwork2.ModelDriven;

public class TeacherAction extends BaseAction implements ModelDriven<Teacher>{
	
	//ע��teacherservice
	private TeacherService teacherService;

	//ģ��������װ
	private Teacher teacher = new Teacher();
	@Override
	public Teacher getModel() {
		// TODO Auto-generated method stub
		return teacher;
	}
	
	
	public String login() {
		try {
			Teacher tea = teacherService.login(teacher.getUsername(), teacher.getPassword());
			if(tea!=null) {
				//����session
				session.setAttribute("loginUser", tea);
				return "login_success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ʧ��
		return LOGIN;
	}


	//������get��set����
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	
	

}
