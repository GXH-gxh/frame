package com.bdqn.dao;

import java.util.List;

import com.bdqn.entity.Grade;

public interface GradeDao {
	
	public static final String SERVICE_NAME = "com.bdqn.dao.impl.GradeDaoImpl";

	/**
	 * ��ѯ�꼶�б�
	 * @return
	 * @throws Exception
	 */
	List<Grade> findGradeList() throws Exception;
}
