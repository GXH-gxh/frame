package com.bdqn.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bdqn.dao.BaseDao;
import com.bdqn.dao.GradeDao;
import com.bdqn.entity.Grade;

@Repository(GradeDao.SERVICE_NAME)
public class GradeDaoImpl extends BaseDao implements GradeDao {
	

	@Override
	public List<Grade> findGradeList() throws Exception {
		// TODO Auto-generated method stub
		return (List<Grade>) this.getHibernateTemplate().find("from Grade");
	}

}
