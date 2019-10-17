package gj.bdqn.erp.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gj.bdqn.erp.biz.DepBiz;
import gj.bdqn.erp.dao.DepDao;
import gj.bdqn.erp.entity.Dep;

/**
 * 
 * 	部门业务逻辑层实现类
 *
 */
@Repository(DepBiz.BIZ_NAME)	// 注入bean
@Transactional	// 开启事物
public class DepBizImpl extends BaseBizImpl<Dep> implements DepBiz{
	
	// 注入DepDao属性
	@Resource
	private DepDao depDao;

	
	@Override
	public Dep findDepByName(String dep_name) {
		return depDao.findDepByName(dep_name);
	}

	@Override
	public Dep findDepByTele(String dep_tele) {
		return depDao.findDepByTele(dep_tele);
	}

}
