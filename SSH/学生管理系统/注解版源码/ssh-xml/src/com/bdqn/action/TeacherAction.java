package com.bdqn.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bdqn.entity.Teacher;
import com.bdqn.service.TeacherService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction implements ModelDriven<Teacher>{
	
	//ע��teacherservice
	@Resource
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


	

}
