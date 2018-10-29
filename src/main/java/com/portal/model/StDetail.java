package com.portal.model;

import com.alibaba.fastjson.annotation.JSONField;

public class StDetail {
	private Integer id;

	private Integer brand_id;

	private String brand_cn;

	private String brand_en;

	private String model;

	private String imei;

	private String imei2;

	private String active_version;

	private String current_version;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String active_time;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String last_time;

	private Integer country_type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_cn() {
		return brand_cn;
	}

	public void setBrand_cn(String brand_cn) {
		this.brand_cn = brand_cn;
	}

	public String getBrand_en() {
		return brand_en;
	}

	public void setBrand_en(String brand_en) {
		this.brand_en = brand_en;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImei2() {
		return imei2;
	}

	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}

	public String getActive_version() {
		return active_version;
	}

	public void setActive_version(String active_version) {
		this.active_version = active_version;
	}

	public String getCurrent_version() {
		return current_version;
	}

	public void setCurrent_version(String current_version) {
		this.current_version = current_version;
	}

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

	public Integer getCountry_type() {
		return country_type;
	}

	public void setCountry_type(Integer country_type) {
		this.country_type = country_type;
	}

}