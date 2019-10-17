package gj.bdqn.erp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import gj.bdqn.erp.dao.BaseDao;
import gj.bdqn.erp.entity.Dep;
/**
 * 
 * 	通用数据访问层接口
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	// 获得T的实际Class
	private Class clazz;
	
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
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	public List<T> pagingQuery(T t, Integer firstResult, Integer maxResults) {
		DetachedCriteria detachedCriteria = getDetachedCriteria(t);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResults);
	}

	@Override
	public Long rowCount(T t) {
		// 获取查询条件
		DetachedCriteria detachedCriteria = getDetachedCriteria(t);
		// 设置统计函数
		detachedCriteria.setProjection(Projections.rowCount());
		return (Long) this.getHibernateTemplate().findByCriteria(detachedCriteria).get(0);
	}

	@Override
	public T findById(Object id) {
		return (T) this.getHibernateTemplate().get(clazz, (Serializable)id);
	}

	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(Object id) {
		// 先查询后删除,让对象转换成持久态
		T t = findById(id);
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	/**
	 * 	给离线查询对象设置查询条件
	 * 	由子类去实现
	 * @param 
	 * @return
	 */
	public  DetachedCriteria getDetachedCriteria(T t) {
		return null;
	}

	@Override
	public Object getId(T t) throws Exception{
		//获得Person的Method对象,参数为方法名,参数列表的类型Class对象
        Method method = clazz.getDeclaredMethod("getId");
        //System.out.println(method.getReturnType()==(Integer.class));
        // 创建T类型的新实例
        T t2 = (T) clazz.newInstance();
        t2 = t;
        // 判断方法返回值类型
        if (method.getReturnType() == Integer.class) {
        	 //invoke方法，参数为clazz实例对象，和想要调用的方法参数,返回值是方法返回值
        	 Integer id = (Integer) method.invoke(t2);
        	 return id;
		}else if (method.getReturnType() == String.class) {
			 //invoke方法，参数为clazz实例对象，和想要调用的方法参数,返回值是方法返回值
			String id = (String) method.invoke(t2);
			return id;
		}
		return null;
	}

}
