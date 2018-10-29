package com.portal.model;

public class SubPer {
	private Integer id;
	private Integer uid;
	private Integer pid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public SubPer(Integer uid, Integer pid) {
		super();
		this.uid = uid;
		this.pid = pid;
	}

}
