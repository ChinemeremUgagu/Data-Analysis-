package com.tyataacademy.java.project.emanagement.model;

import java.io.Serializable;
import java.util.Date;

public class Compliance  implements Serializable{
	private Integer id;
	private String rltype;
	private String details;
	private Date createDate;
	private Integer departmentId;

	public Compliance() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRltype() {
		return rltype;
	}

	public void setRltype(String rltype) {
		this.rltype = rltype;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
