package com.bdqn.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class GradeAction2 extends BaseAction{

	private String gradeName;
	
	
	public String check() {
		if(gradeName.equals("S1")) {
			this.jsonMap.put("state", true);//´æÔÚ
		}else {
			this.jsonMap.put("state", false);//²»´æÔÚ
		}
		return SUCCESS;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
