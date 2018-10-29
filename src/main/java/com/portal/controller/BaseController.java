package com.portal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.model.LogData;
import com.portal.model.User;
import com.portal.service.IUserService;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/baseController")
public class BaseController {
	private static Log log = LogFactory.getLog(BaseController.class);

	@Resource
	private IUserService userService;

	@Autowired
	public HttpServletRequest request;

	/**
	 * 操作记录
	 * 
	 * @param logdata
	 */
	public void logMsg(LogData logdata) {
		userService.logMsg(logdata);
		log.info(logdata.getCn_msg());
	}

	public User getUser() {
		User user = new User();
		user = (User) request.getSession().getAttribute("cUserSession");
		return user;
	}

	public String getIp() {
		return request.getHeader("X-Real-IP");
	}

	public String getLang() {
		String lang = (String) request.getSession().getAttribute("lang");
		if (StringUtils.isEmpty(lang) || lang.equals("cn")) {
			lang = "cn";
		} else {
			lang = "en";
		}
		return lang;
	}

	public void setMessage(int res, JSONObject jsonObject){
		if (res != 0) {
			jsonObject.put("message", "success");
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("message", "error");
			jsonObject.put("status", "error");
		}
	}
	
	public void setMessage(boolean result, JSONObject jsonObject){
		if (result) {
			jsonObject.put("message", "success");
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("message", "error");
			jsonObject.put("status", "error");
		}
	}

}
