package gj.bdqn.erp.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import gj.bdqn.erp.biz.BaseBiz;

/**
 * 	通用Controller
 * @author Administrator
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	// 获得泛型T的实际Class
	private Class clazz;
	public BaseAction() {
		// 获得调用的Class,谁调用this指代谁
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		//System.out.println(type);
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] types = parameterizedType.getActualTypeArguments();
		this.clazz = (Class) types[0];
	}

	// 模型驱动使用的对象
	private T t;
	// 提供set方法让子类给模型驱动赋值
	public void setT(T t) {
		this.t = t;
	}
	@Override
	public T getModel() {
		return t;
	}

	// 注入biz,泛型T传的是什么就是操作什么的biz
	@Resource
	private BaseBiz<T> baseBiz;

	// 表达式封装easyui提供的分业属性
	private int page;// 当前页码
	private int rows;// 每页显示数量
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 	响应查询所有请求
	 */
	public void findAll() {
		// 1.查询所有部门数据
		List<T> deps = baseBiz.findAll();
		// 2.将数据转换成JSON格式
		String depsJson = JSON.toJSONString(deps);
		// 3.将数据返回页面
		responsePage(depsJson);
	}

	/**
	 * 	响应分页模糊查询请求
	 */
	public void pagingQuery() {
		//System.out.println(page+"..."+rows);
		// 起始页 = (当前页数 - 1) * 每页显示数量
		Integer firstResult = (page - 1)*rows;
		Map<String, Object> map = new HashMap<>();
		// 总记录数
		long total = baseBiz.rowCount(t);
		map.put("total", total);
		// 获取数据响应页面,部门名称和部门电话都封装在模型驱动的对象中
		List<T> deps = baseBiz.pagingQuery(t,firstResult,rows);
		map.put("rows", deps);
		responsePage(JSON.toJSONString(map));
	} 

	/**
	 * 	响应新增请求
	 */
	public void add() {
		try {
			baseBiz.add(t);
			getPageResult(true, "添加成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getPageResult(false, "添加失败!");
		}
	}

	/**
	 * 	响应删除请求
	 */
	public void delete() {
		try {
			baseBiz.delete(baseBiz.getId(t));
			getPageResult(true, "删除成功!");
		} catch (Exception e) {
			getPageResult(false, "删除失败!");
			e.printStackTrace();
		}
	}


	/**
	 * 	根据编号查询对象
	 */
	public void findById() {
		try {
			responsePage(JSON.toJSONString( baseBiz.findById(baseBiz.getId(t))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	响应修改部门请求
	 */
	public void update() {
		try {
			baseBiz.update(t);
			getPageResult(true, "修改成功!");
		} catch (Exception e) {
			getPageResult(false, "修改失败!");
			e.printStackTrace();
		}
	}


	/**
	 * 	设置方法执行结果和信息
	 * @param result
	 * @param message
	 */
	public void getPageResult(boolean result,String message) {
		// 创建map集合
		Map<String, Object> map = new HashMap<>();
		// 设置方法执行结果
		map.put("result", result);
		// 设置结果信息
		map.put("message", message);
		// 调用方法将数据返回页面
		responsePage(JSON.toJSONString(map));
	}


	/**
	 * 	向页面返回数据
	 * @param str
	 */
	public void responsePage(String str) {
		try {
			// 获取response对象
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置响应字符集编码
			response.setContentType("text/html;charset=utf-8");
			// 将数据返回页面
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
