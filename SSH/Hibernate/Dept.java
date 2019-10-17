package com.bdqn.entity;

import java.io.Serializable;

/**
 * 	部门类
 * @author 13268
 *
 */
public class Dept implements Serializable{
	
	private Integer deptNo;//部门编号
	private String dName;//部门名称
	private String loc;//位置
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Dept [deptNo=" + deptNo + ", dName=" + dName + ", loc=" + loc + "]";
	}
	public Dept(Integer deptNo, String dName, String loc) {
		super();
		this.deptNo = deptNo;
		this.dName = dName;
		this.loc = loc;
	}
	public Dept() {
		super();
	}
	public Dept(String dName, String loc) {
		super();
		this.dName = dName;
		this.loc = loc;
	}
	
	

}
