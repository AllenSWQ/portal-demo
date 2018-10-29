package com.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.model.LogData;
import com.portal.model.Model;
import com.portal.model.User;
import com.portal.service.IModelService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/modelController")
public class ModelController extends BaseController {
	private static Log log = LogFactory.getLog(ModelController.class);

	@Resource
	private IModelService modelService;

	HttpSession session;

	@RequestMapping("model")
	public String model() {
		return "model/model";
	}

	/**
	 * 机型列表
	 * 
	 * @param search
	 * @param offset
	 * @param limit
	 * @param sort
	 * @param order
	 * @param filter
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modelList")
	@ResponseBody
	public String modelList(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String channel_id, String model) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			List<Model> list = modelService.getAll(search, offset, limit, sort,
					order, filter, brand_id, channel_id, model, m_user_id);
			Integer count = modelService.getCount(search, filter, brand_id,
					channel_id, model, m_user_id);
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询机型管理", "search Model Mngt", 1, 1));
		} catch (Exception ex) {
			log.error("modelList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 机型下拉框
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modelSelect")
	@ResponseBody
	public String modelSelect(
			@RequestParam(value = "brand_id") String brand_id,
			@RequestParam(value = "channel_id", required = false) String channel_id)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<Model> model = new ArrayList<>();
			
			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			model = modelService.getSelect(brand_id, channel_id, user_id, m_user_id);
			
			jsonObject.put("rows", model);
		} catch (Exception ex) {
			log.error("modelSelect error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 修改机型-联网周期
	 * 
	 * @param channelDetail
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editModel", method = RequestMethod.POST)
	@ResponseBody
	public String editModel(@ModelAttribute("id") String id, @ModelAttribute("cycle") String cycle)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = modelService.updateModel(id, cycle);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"配置机型联网周期: id = " + id + ", cycle =" + cycle,
					"update model cycle: id = " + id + ", cycle =" + cycle, 3, res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editModel error : " + ex);
		}
		return jsonObject.toString();
	}

}
