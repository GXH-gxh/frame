package com.bdqn.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bdqn.entity.Grade;
import com.bdqn.entity.Student;
import com.bdqn.service.GradeService;
import com.bdqn.service.StudentService;
import com.bdqn.vo.StudentQueryVO;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction{
	
	/*************封装页面参数开始********************/
	//表达式封装
	private StudentQueryVO stuQueryVO;
	//封装页面表单数据
	private Student student;
	/*************封装页面参数结束********************/
	
	//学生列表集合，存在值栈中，可直接使用
	private List<Student> studentList;
	//年级列表集（将信息显示到下拉列表中）
	private List<Grade> gradeList;
	
	
	//注入studentService
	@Resource
	private StudentService studentService;

	
	//注入gradeService
	@Resource
	private GradeService gradeService;


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

	/**
	 * 去到添加页面
	 * @return
	 */
	public String toadd() {
		try {
			//调用查询年级列表的方法
			gradeList = gradeService.findGradeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回到添加页面
		return "add";
	}
	
	/**
	 * 添加学生信息
	 * @return
	 */
	public String add() {
		try {
			//成功返回到列表页面
			if(studentService.addStudent(student)) {
				return "tolist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//失败，返回到去到添加页面
		return "toadd";
	}
	
	/**
	 * 去到修改页面
	 * @return
	 */
	public String toupdate() {
		try {
			//调用查询年级列表的方法
			gradeList = gradeService.findGradeList();
			//根据id查询学生信息
			student = studentService.findStudentById(student.getId());
			//返回到修改页面
			return "update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
	}
	
	/**
	 *  修改学生信息
	 * @return
	 */
	public String update() {
		try {
			if(studentService.updateStudent(student)) {
				//修改成功，返回列表页面
				return "tolist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toupdate";
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
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	
}
