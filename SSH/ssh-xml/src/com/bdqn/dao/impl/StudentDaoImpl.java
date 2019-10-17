package com.bdqn.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.bdqn.dao.StudentDao;
import com.bdqn.entity.Student;
import com.bdqn.utils.PageBean;
import com.bdqn.vo.StudentQueryVO;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao{

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCount(StudentQueryVO student) throws Exception {
		// TODO Auto-generated method stub
		List<Student> list = (List<Student>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				//����hql���
				String hql = "from Student s  join fetch s.grade where 1=1";
				//�ж�ѧ�����������Ƿ�Ϊ��
				if(student!=null) {
					//ѧ������
					if(student.getStudentname()!=null && !student.getStudentname().equals("")) {
						hql +=" and s.studentname like '%"+student.getStudentname()+"%'";
					}
				}
				//����Query����
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		//���ؼ���
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentList(StudentQueryVO student, PageBean page) throws Exception {
		// TODO Auto-generated method stub
		List<Student> list = (List<Student>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				//����hql���
				String hql = "from Student s  join fetch s.grade where 1=1";
				//�ж�ѧ�����������Ƿ�Ϊ��
				if(student!=null) {
					//ѧ������
					if(student.getStudentname()!=null && !student.getStudentname().equals("")) {
						hql +=" and s.studentname like '%"+student.getStudentname()+"%'";
					}
				}
				hql +=" order by s.id";//����ѧ������
				//����Query����
				Query query = session.createQuery(hql);
				//���õ�ǰҳ��
				query.setFirstResult(page.getFirstPage());
				//����ÿҳ��ʾ������
				query.setMaxResults(page.getPageSize());
				return query.list();
			}
		});
		//���ؼ���
		return list;
	}

}
