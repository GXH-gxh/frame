package com.bdqn.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class GradeAction extends BaseAction{
	
	//�꼶����
	private String gradeName;
	
	//��һ��:���InputStream���͵ĳ�Ա����
	private InputStream inputStream;
	//�ڶ������ṩ��Ӧ��get��set����
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String checkGradeName() throws Exception {
		//�������ݿ������Ƚϣ������꼶���Ʋ�ѯ�꼶��Ϣ
		//Grade grade = gradeService.findGradeByName(gradeName);
		//if(grade!=null)//����
		
		String flag = null;//�Ƿ���ڵı�ʶ��
		if(gradeName.equals("S1")) {
			flag = "true";//����
		}else {
			flag = "false";//������
		}
		//��������ͨ������������ص�ҳ��
		inputStream = new ByteArrayInputStream(flag.getBytes("utf-8"));
		return SUCCESS;
	}
	
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	

	
	
}
