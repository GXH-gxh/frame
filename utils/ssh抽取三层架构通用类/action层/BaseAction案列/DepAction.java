package gj.bdqn.erp.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import gj.bdqn.erp.biz.DepBiz;
import gj.bdqn.erp.entity.Dep;
@Controller("depAction")	// 由spring创建
@Scope("prototype")	//多列模式
public class DepAction extends BaseAction<Dep>{
	
	// 给父类模型驱动赋值
	public DepAction() {
		super.setT(new Dep());
	}
	
	// 注入DepBiz,如果私有方法父类的通用Biz不能够满足需求,需要自己注入一个指定需求的Biz
	@Resource
	private DepBiz depBiz;
	
	
	/**
	 * 	响应新增时检测部门名称是否存在
	 */
	public void findDepByName() {
		// 判断对象是否存在,存在表示部门名称已存在
		if(depBiz.findDepByName(super.getModel().getDep_name()) != null) {
			super.getPageResult(false, "");
		}else {
			super.getPageResult(true, "");
		}
	}
	
	/**
	 * 	响应新增时检测部门电话是否存在
	 */
	public void findDepByTele() {
		// 判断对象是否存在,存在表示部门电话已存在
		if(depBiz.findDepByTele(super.getModel().getDep_tele()) != null) {
			super.getPageResult(false, "");
		}else {
			super.getPageResult(true, "");
		}
	}	
}
