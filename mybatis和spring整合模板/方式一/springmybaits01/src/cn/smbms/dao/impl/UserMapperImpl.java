package cn.smbms.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import cn.smbms.dao.UserMapper;
import cn.smbms.entity.User;

public class UserMapperImpl implements UserMapper{
	
	//向spring注入sqlSession
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		//命名空间+"."+方法标签的id
		return this.sqlSession.selectList("cn.smbms.dao.UserMapper.findUserList");
	}

}
