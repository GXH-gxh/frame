package gj.bdqn.erp.dao;

import gj.bdqn.erp.entity.Dep;
/**
 * 
 * 	部门数据访问层
 *
 */
public interface DepDao extends BaseDao<Dep>{
	// 部门数据访问层实现类的beanId
	public static final String DAO_NAME = "gj.bdqn.erp.dao.impl.DepDaoImpl";

	/**
	 * 	根据部门名称查询部门
	 * @param dep_name
	 * @return
	 */
	public Dep findDepByName(String dep_name);
	
	/**
	 * 	根据部门电话查询部门
	 * @param dep_tele
	 * @return
	 */
	public Dep findDepByTele(String dep_tele);
}
