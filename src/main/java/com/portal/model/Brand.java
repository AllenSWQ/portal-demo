package com.portal.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class Brand {
	private Integer id;
	private String brand_en;
	private String brand_cn;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand_en() {
		return brand_en;
	}

	public void setBrand_en(String brand_en) {
		this.brand_en = brand_en;
	}

	public String getBrand_cn() {
		return brand_cn;
	}

	public void setBrand_cn(String brand_cn) {
		this.brand_cn = brand_cn;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", brand_en=" + brand_en + ", brand_cn="
				+ brand_cn + "]";
	}

}