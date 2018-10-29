package com.portal.model;

public class LogData {
	private Integer user_id;
	private String user_name;
	private String ip;
	private String cn_msg;
	private String en_msg;
	private Integer status = 1;
	private Integer result = 1;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCn_msg() {
		return cn_msg;
	}

	public void setCn_msg(String cn_msg) {
		this.cn_msg = cn_msg;
	}

	public String getEn_msg() {
		return en_msg;
	}

	public void setEn_msg(String en_msg) {
		this.en_msg = en_msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public LogData(Integer user_id, String user_name, String ip, String cn_msg,
			String en_msg, Integer status, Integer result) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip = ip;
		this.cn_msg = cn_msg;
		this.en_msg = en_msg;
		this.status = status;
		this.result = result;
	}

}