package com.portal.model;

public class StChannel {
	private Integer id;

	private Integer brand_id;

	private String brand_cn;

	private String brand_en;

	private String model;

	private String channel;

	private Integer active_num;

	private String active_ratio;

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getActive_num() {
		return active_num;
	}

	public void setActive_num(Integer active_num) {
		this.active_num = active_num;
	}

	public String getActive_ratio() {
		return active_ratio;
	}

	public void setActive_ratio(String active_ratio) {
		this.active_ratio = active_ratio;
	}

}