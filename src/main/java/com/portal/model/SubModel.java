package com.portal.model;

public class SubModel {
	private Integer id;
	private Integer uid;
	private String model;
	
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public SubModel(Integer uid, String model) {
		super();
		this.uid = uid;
		this.model = model;
	}

}
