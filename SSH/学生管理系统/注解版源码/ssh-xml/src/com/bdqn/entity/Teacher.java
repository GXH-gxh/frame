package com.bdqn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 教师类
 * @author 梁和营
 *
 * 2019年5月2日下午9:25:05
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
