package gj.ssh.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import gj.ssh.entity.PageBean;

/**
 * 	ͨ��Dao�ӿ�
 * @author Administrator
 *
 */
public interface BaseDao<T> {
	
	// ͨ�ñ��淽��
	void save(T t);
	
	// ͨ���޸ķ���
	void update(T t);
	
	// ͬ��ɾ������
	void delete(T t);
	
	// ͨ�ø���Id��ѯ���󷽷�
	T findById(Serializable id);
	
	// ͨ�ò�ѯ���з���
	List<T> findAll();
	
	// ͨ��ͳ�Ƹ�������
	Long rowCount(DetachedCriteria detachedCriteria);
	
	// ͨ�÷�ҵ��ѯ����
	List<T> findAllByPage(DetachedCriteria detachedCriteria,PageBean<T> pageBean);
}
