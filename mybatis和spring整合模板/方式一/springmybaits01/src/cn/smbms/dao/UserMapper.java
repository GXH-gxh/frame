package cn.smbms.dao;

import java.util.List;

import cn.smbms.entity.User;

public interface UserMapper {

	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<User> findUserList();
}
