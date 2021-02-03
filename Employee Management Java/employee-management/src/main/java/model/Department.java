package com.tyataacademy.java.project.emanagement.model;

import java.io.Serializable;

public class Department implements Serializable{
	private Integer id;
	private String deptName;
	
	public Department() {
		super();
	}

	public Department(Integer id, String deptName) {
		super();
		this.id = id;
		this.deptName = deptName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
