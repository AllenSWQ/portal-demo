package com.portal.model;

import java.sql.Timestamp;

public class Permission {
	private Integer id;
	private String cn_name;
	private String en_name;
	private String describe;
	private String icon;
	private Integer state;
	private Integer flevel;
	private String url;
	private Integer ptype;
	private Timestamp create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCn_name() {
		return cn_name;
	}

	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFlevel() {
		return flevel;
	}

	public void setFlevel(Integer flevel) {
		this.flevel = flevel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? "" : url.trim();
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", cn_name=" + cn_name + ", en_name="
				+ en_name + ", describe=" + describe + ", icon=" + icon
				+ ", state=" + state + ", flevel=" + flevel + ", url=" + url
				+ ", ptype=" + ptype + ", create_time=" + create_time + "]";
	}

}
