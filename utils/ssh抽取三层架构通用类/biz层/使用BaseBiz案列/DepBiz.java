package gj.bdqn.erp.biz;

import gj.bdqn.erp.entity.Dep;
/**
 * 
 * 	部门业务逻辑层
 *
 */
public interface DepBiz extends BaseBiz<Dep>{
	// 部门业务逻辑层实现类beanId
	public static final String BIZ_NAME = "gj.bdqn.erp.biz.impl.DepBizImpl";
	
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
