package com.portal.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

public class ChannelFile {
	private Integer id;
	private Integer channel_id;
	private String old_file_name;
	private String new_file_name;
	private String file_size;
	private String file_url;
	private Integer is_del;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp create_time;

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

	public String getOld_file_name() {
		return old_file_name;
	}

	public void setOld_file_name(String old_file_name) {
		this.old_file_name = old_file_name;
	}

	public String getNew_file_name() {
		return new_file_name;
	}

	public void setNew_file_name(String new_file_name) {
		this.new_file_name = new_file_name;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "ChannelFile [id=" + id + ", channel_id=" + channel_id
				+ ", old_file_name=" + old_file_name + ", new_file_name="
				+ new_file_name + ", file_size=" + file_size + ", file_url="
				+ file_url + ", is_del=" + is_del + ", create_time="
				+ create_time + "]";
	}

}
