package gj.bdqn.erp.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import gj.bdqn.erp.dao.DepDao;
import gj.bdqn.erp.entity.Dep;

/**
 * 
 * 	部门数据访问层实现类
 *
 */
@Repository(DepDao.DAO_NAME)// 注入bean中
public class DepDaoImpl extends BaseDaoImpl<Dep> implements DepDao{
	
	// 获取sessionFactory
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 	给部门离线查询对象设置查询条件
	 * @param dep
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Dep dep) {
		// 创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dep.class);
		/**
		 * 	 判断对象是否为空
		 */
		if (dep != null) {
			// 判断部门名称是否为空
			if (dep.getDep_name() != null && dep.getDep_name().trim().length() > 0) {
				detachedCriteria.add(Restrictions.like("dep_name", dep.getDep_name(),MatchMode.ANYWHERE));
			}
			// 判断部门电话是否为空
			if (dep.getDep_tele() != null && dep.getDep_tele().trim().length() > 0) {
				detachedCriteria.add(Restrictions.like("dep_tele", dep.getDep_tele(),MatchMode.ANYWHERE));
			}
		}
		
		return detachedCriteria;
	}

	@Override
	public Dep findDepByName(String dep_name) {
		List<Dep> deps = (List<Dep>) this.getHibernateTemplate().find("from Dep where dep_name = ?", dep_name);
		if(deps.size() > 0) {
			return deps.get(0);
		}
		return null;
	}
	
	@Override
	public Dep findDepByTele(String dep_tele) {
		List<Dep> deps = (List<Dep>) this.getHibernateTemplate().find("from Dep where dep_tele = ?", dep_tele);
		if (deps.size() > 0) {
			return deps.get(0);
		}
		return null;
	}

}
