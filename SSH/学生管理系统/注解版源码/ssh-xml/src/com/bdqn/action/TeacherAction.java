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
	
	//注入teacherservice
	@Resource
	private TeacherService teacherService;

	//模型驱动封装
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
				//保存session
				session.setAttribute("loginUser", tea);
				return "login_success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//失败
		return LOGIN;
	}


	//以下是get、set方法
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	

}
