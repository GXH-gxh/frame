package com.bdqn.dao;

import java.util.List;

import com.bdqn.entity.Grade;

public interface GradeDao {
	
	public static final String SERVICE_NAME = "com.bdqn.dao.impl.GradeDaoImpl";

	/**
	 * 查询年级列表
	 * @return
	 * @throws Exception
	 */
	List<Grade> findGradeList() throws Exception;
}
