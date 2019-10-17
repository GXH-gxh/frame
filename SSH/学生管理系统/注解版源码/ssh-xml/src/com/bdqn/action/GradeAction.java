package com.bdqn.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class GradeAction extends BaseAction{
	
	//年级名称
	private String gradeName;
	
	//第一步:添加InputStream类型的成员变量
	private InputStream inputStream;
	//第二步：提供相应的get、set方法
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String checkGradeName() throws Exception {
		//若从数据库中作比较，根据年级名称查询年级信息
		//Grade grade = gradeService.findGradeByName(gradeName);
		//if(grade!=null)//存在
		
		String flag = null;//是否存在的标识符
		if(gradeName.equals("S1")) {
			flag = "true";//存在
		}else {
			flag = "false";//不存在
		}
		//第三步：通过流将结果返回到页面
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
