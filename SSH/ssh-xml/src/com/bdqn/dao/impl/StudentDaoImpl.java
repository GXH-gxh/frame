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
				//定义hql语句
				String hql = "from Student s  join fetch s.grade where 1=1";
				//判断学生条件对象是否为空
				if(student!=null) {
					//学生姓名
					if(student.getStudentname()!=null && !student.getStudentname().equals("")) {
						hql +=" and s.studentname like '%"+student.getStudentname()+"%'";
					}
				}
				//创建Query对象
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		//返回集合
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
				//定义hql语句
				String hql = "from Student s  join fetch s.grade where 1=1";
				//判断学生条件对象是否为空
				if(student!=null) {
					//学生姓名
					if(student.getStudentname()!=null && !student.getStudentname().equals("")) {
						hql +=" and s.studentname like '%"+student.getStudentname()+"%'";
					}
				}
				hql +=" order by s.id";//根据学号排序
				//创建Query对象
				Query query = session.createQuery(hql);
				//设置当前页码
				query.setFirstResult(page.getFirstPage());
				//设置每页显示的数量
				query.setMaxResults(page.getPageSize());
				return query.list();
			}
		});
		//返回集合
		return list;
	}

}
