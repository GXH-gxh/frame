package com.bdqn.service;

import java.util.List;

import com.bdqn.entity.Grade;

public interface GradeService {
	
	public static final String SERVICE_NAME = "com.bdqn.service.impl.GradeServiceImpl";
	
	/**
	 * 查询年级列表
	 * @return
	 * @throws Exception
	 */
	List<Grade> findGradeList() throws Exception;
}
