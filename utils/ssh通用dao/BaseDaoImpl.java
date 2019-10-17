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
 * 	通用Dao实现类	
 * @author Administrator
 *
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
   
	private Class clazz;
	/*public  BaseDaoImpl(Class clazz) {
		this.clazz = clazz;
	}*/
	
	// 获取调用类的泛型类型
	public  BaseDaoImpl() {
		// 获得调用的Class,谁调用this指代谁
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		//System.out.println(type);
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] types = parameterizedType.getActualTypeArguments();
		this.clazz = (Class) types[0];
	}
	
	@Override
	// 保存
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	// 修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	// 删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	// 根据iD查询对象
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	// 查询所有
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	// 统计数据行数
	public Long rowCount(DetachedCriteria detachedCriteria) {
		// 设置离线条件查询
		detachedCriteria.setProjection(Projections.rowCount());
		List list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return (Long) list.get(0);
		}
		return null;
	}

	@Override
	// 分页查询
	public List<T> findAllByPage(DetachedCriteria detachedCriteria,PageBean<T> pageBean) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, pageBean.getBeingPage(),pageBean.getPageSize());
	}
	
	
}
