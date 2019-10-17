package gj.ssh.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import gj.ssh.entity.PageBean;

/**
 * 	通用Dao接口
 * @author Administrator
 *
 */
public interface BaseDao<T> {
	
	// 通用保存方法
	void save(T t);
	
	// 通用修改方法
	void update(T t);
	
	// 同用删除方法
	void delete(T t);
	
	// 通用根据Id查询对象方法
	T findById(Serializable id);
	
	// 通用查询所有方法
	List<T> findAll();
	
	// 通用统计个数方法
	Long rowCount(DetachedCriteria detachedCriteria);
	
	// 通用分业查询方法
	List<T> findAllByPage(DetachedCriteria detachedCriteria,PageBean<T> pageBean);
}
