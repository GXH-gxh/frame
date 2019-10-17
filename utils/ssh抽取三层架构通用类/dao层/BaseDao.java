package gj.bdqn.erp.dao;

import java.io.Serializable;
import java.util.List;

import gj.bdqn.erp.entity.Dep;

/**
 * 	
 * 	通用数据访问层接口
 *
 */
public interface BaseDao<T> {
	
	/**
	 * 	查询所有
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 	分业查询
	 * @return
	 */
	public List<T> pagingQuery(T t,Integer firstResult,Integer maxResults);
	
	/**
	 * 	查询总数据行数
	 * @param
	 * @return
	 */
	public Long rowCount(T t);
	
	/**
	 * 	根据id查询
	 * @param id
	 * @return
	 */
	public T findById(Object id);
	
	/**
	 * 	添加
	 * @param dep
	 */
	public void add(T t);
	
	/**
	 * 	删除
	 * @param id
	 */
	public void delete(Object id);
	
	/**
	 * 	修改
	 * @param 
	 */
	public void update(T t);
	
	/**
	 * 	通过反射获取Id
	 * @param clazz
	 * @return
	 */
	public Object getId(T t)throws Exception;
	
}
