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
	
	/*************��װҳ�������ʼ********************/
	//���ʽ��װ
	private StudentQueryVO stuQueryVO;
	//��װҳ�������
	private Student student;
	/*************��װҳ���������********************/
	
	//ѧ���б��ϣ�����ֵջ�У���ֱ��ʹ��
	private List<Student> studentList;
	//�꼶�б�������Ϣ��ʾ�������б��У�
	private List<Grade> gradeList;
	
	
	//ע��studentService
	@Resource
	private StudentService studentService;

	
	//ע��gradeService
	@Resource
	private GradeService gradeService;


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

	/**
	 * ȥ�����ҳ��
	 * @return
	 */
	public String toadd() {
		try {
			//���ò�ѯ�꼶�б�ķ���
			gradeList = gradeService.findGradeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���ص����ҳ��
		return "add";
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @return
	 */
	public String add() {
		try {
			//�ɹ����ص��б�ҳ��
			if(studentService.addStudent(student)) {
				return "tolist";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ʧ�ܣ����ص�ȥ�����ҳ��
		return "toadd";
	}
	
	/**
	 * ȥ���޸�ҳ��
	 * @return
	 */
	public String toupdate() {
		try {
			//���ò�ѯ�꼶�б�ķ���
			gradeList = gradeService.findGradeList();
			//����id��ѯѧ����Ϣ
			student = studentService.findStudentById(student.getId());
			//���ص��޸�ҳ��
			return "update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
	}
	
	/**
	 *  �޸�ѧ����Ϣ
	 * @return
	 */
	public String update() {
		try {
			if(studentService.updateStudent(student)) {
				//�޸ĳɹ��������б�ҳ��
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
