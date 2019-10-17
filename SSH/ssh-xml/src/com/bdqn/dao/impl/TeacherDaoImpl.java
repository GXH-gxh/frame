package com.bdqn.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.bdqn.dao.TeacherDao;
import com.bdqn.entity.Teacher;

/**
 * ��ʦ�ӿ�ʵ����
 * @author KazuGin
 *
 */
public class TeacherDaoImpl extends HibernateDaoSupport implements TeacherDao{

	@Override
	public Teacher findTeacherByName(String username) throws Exception {
		// TODO Auto-generated method stub
		//��ѯ
		List<Teacher> list =  (List<Teacher>) this.getHibernateTemplate().find("from Teacher t where t.username = ?", username);
		//�жϼ����Ƿ�������
		if(list.size()>0) {
			//���ؽ���еĵ�һ������
			return list.get(0);
		}
		//û������
		return null;
	}

}
