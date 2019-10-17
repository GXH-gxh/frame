package com.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdqn.dao.GradeDao;
import com.bdqn.entity.Grade;
import com.bdqn.service.GradeService;

@Service(GradeService.SERVICE_NAME)
@Transactional(readOnly=false)
public class GradeServiceImpl implements GradeService{
	
	
	@Resource
	private GradeDao gradeDao;



	@Override
	public List<Grade> findGradeList() throws Exception {
		// TODO Auto-generated method stub
		return gradeDao.findGradeList();
	}

}
