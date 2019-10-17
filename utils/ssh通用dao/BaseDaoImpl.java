package gj.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import gj.ssh.dao.BaseDao;
import gj.ssh.entity.PageBean;
/**
 * 	ͨ��Daoʵ����	
 * @author Administrator
 *
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
   
	private Class clazz;
	/*public  BaseDaoImpl(Class clazz) {
		this.clazz = clazz;
	}*/
	
	// ��ȡ������ķ�������
	public  BaseDaoImpl() {
		// ��õ��õ�Class,˭����thisָ��˭
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		//System.out.println(type);
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] types = parameterizedType.getActualTypeArguments();
		this.clazz = (Class) types[0];
	}
	
	@Override
	// ����
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	// �޸�
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	// ɾ��
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	// ����iD��ѯ����
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	// ��ѯ����
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	// ͳ����������
	public Long rowCount(DetachedCriteria detachedCriteria) {
		// ��������������ѯ
		detachedCriteria.setProjection(Projections.rowCount());
		List list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return (Long) list.get(0);
		}
		return null;
	}

	@Override
	// ��ҳ��ѯ
	public List<T> findAllByPage(DetachedCriteria detachedCriteria,PageBean<T> pageBean) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, pageBean.getBeingPage(),pageBean.getPageSize());
	}
	
	
}
