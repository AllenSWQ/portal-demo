package com.portal.model;

import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private Integer id;

	private String name;

	private String pwd;

	private String newpwd;

	private Integer brand_id;

	private String brand_cn;

	private String brand_en;

	private String email;

	private String contact;

	private String phone;

	private Integer state;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp create_time;

	private Role roles;

	private Integer roleid;

	private String rolename;

	private String verficode;

	private String lang;

	private Integer fuser;

	private String model;

	private String per;

	private Integer type;
	
	private String brand_array;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
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
		this.brand_cn = brand_cn == null ? null : brand_cn.trim();
	}

	public String getBrand_en() {
		return brand_en;
	}

	public void setBrand_en(String brand_en) {
		this.brand_en = brand_en == null ? null : brand_en.trim();
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = StringUtils.isEmpty(contact) ? null : contact.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = StringUtils.isEmpty(email) ? null : email.trim();
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = StringUtils.isEmpty(rolename) ? null : rolename.trim();
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getVerficode() {
		return verficode;
	}

	public void setVerficode(String verficode) {
		this.verficode = verficode;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFuser() {
		return fuser;
	}

	public void setFuser(Integer fuser) {
		this.fuser = fuser;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBrand_array() {
		return brand_array;
	}

	public void setBrand_array(String brand_array) {
		this.brand_array = brand_array;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", brand_id=" + brand_id + ", email=" + email + ", contact="
				+ contact + ", phone=" + phone + ", state=" + state
				+ ", fuser=" + fuser + ", type=" + type + ", brand_array="
				+ brand_array + "]";
	}

}