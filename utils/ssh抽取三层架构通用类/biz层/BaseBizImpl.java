package gj.bdqn.erp.biz.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import gj.bdqn.erp.biz.BaseBiz;
import gj.bdqn.erp.dao.BaseDao;
/**
 * 	通用业务逻辑层实现类
 * @author Administrator
 *
 * @param <T>
 */
@Transactional
public class BaseBizImpl<T> implements BaseBiz<T>{
	
	// 注入dao,泛型T传的是那个什么就是操作什么类型的Dao
	@Resource
	private BaseDao<T> baseDao;
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public List<T> pagingQuery(T t, Integer firstResult, Integer maxResults) {
		return baseDao.pagingQuery(t,firstResult,maxResults);
	}

	@Override
	public Long rowCount(T t) {
		return baseDao.rowCount(t);
	}

	@Override
	public void add(T t) {
		baseDao.add(t);
	}

	@Override
	public void delete(Object id) {
		baseDao.delete(id);
	}

	@Override
	public T findById(Object id) {
		return baseDao.findById(id);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public Object getId(T t) throws Exception {
		return baseDao.getId(t);
	}

}
