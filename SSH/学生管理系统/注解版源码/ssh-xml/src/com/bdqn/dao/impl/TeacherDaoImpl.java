package com.bdqn.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bdqn.dao.BaseDao;
import com.bdqn.dao.TeacherDao;
import com.bdqn.entity.Teacher;

/**
 * 教师接口实现类
 * @author KazuGin
 *
 */
@Repository(TeacherDao.SERVICE_NAME)
public class TeacherDaoImpl extends BaseDao implements TeacherDao{

	@Override
	public Teacher findTeacherByName(String username) throws Exception {
		// TODO Auto-generated method stub
		//查询
		List<Teacher> list =  (List<Teacher>) this.getHibernateTemplate().find("from Teacher t where t.username = ?", username);
		//判断集合是否有数据
		if(list.size()>0) {
			//返回结果中的第一条数据
			return list.get(0);
		}
		//没有数据
		return null;
	}

}
