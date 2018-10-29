package com.portal.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class ChannelDetail {
	private Integer id;

	private Integer channel_id;

	private String channel;

	private String version;

	private String content;

	private Integer is_product;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIs_product() {
		return is_product;
	}

	public void setIs_product(Integer is_product) {
		this.is_product = is_product;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "ChannelDetail [id=" + id + ", channel_id=" + channel_id
				+ ", channel=" + channel + ", version=" + version
				+ ", content=" + content + ", is_product=" + is_product
				+ ", update_time=" + update_time + "]";
	}

}