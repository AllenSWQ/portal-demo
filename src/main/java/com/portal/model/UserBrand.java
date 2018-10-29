package com.portal.model;

public class UserBrand {
	private Integer id;
	private Integer userid;
	private Integer brandid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	public UserBrand(Integer userid, Integer brandid) {
		super();
		this.userid = userid;
		this.brandid = brandid;
	}

}
