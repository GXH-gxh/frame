package com.bdqn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * ��ʦ��
 * @author ����Ӫ
 *
 * 2019��5��2������9:25:05
 */
@Entity
public class Teacher {

	@Id
	@GeneratedValue(generator="pk")
	@GenericGenerator(name="pk",strategy="increment")
	private Integer id;
	private String username;
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
