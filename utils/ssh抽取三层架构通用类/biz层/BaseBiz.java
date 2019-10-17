package gj.bdqn.erp.biz;

import java.io.Serializable;
import java.util.List;

/**
 * 	通用业务逻辑层接口
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseBiz<T>{
	
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
	 * @param dep
	 * @return
	 */
	public Long rowCount(T t);
	
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
	 * 	根据id查询对象
	 * @param id
	 * @return
	 */
	public T findById(Object id);
	
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
