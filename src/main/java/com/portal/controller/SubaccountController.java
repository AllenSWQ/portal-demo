package com.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.RolePer;
import com.portal.model.SubModel;
import com.portal.model.SubPer;
import com.portal.model.User;
import com.portal.service.ISubaccountService;
import com.portal.service.IUserService;
import com.portal.util.JavaMailSender;
import com.portal.util.SecurityUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/subaccountController")
public class SubaccountController extends BaseController {
	private static Log log = LogFactory.getLog(SubaccountController.class);

	@Resource
	private ISubaccountService subaccountService;
	
	@Resource
	private IUserService userService;

	HttpSession session;

	@RequestMapping("subaccount")
	public String subaccount() {
		return "subaccount/subaccount";
	}
	
	@RequestMapping("subper")
	public String subper() {
		return "subaccount/subper";
	}

	/**
	 * 子账户列表
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
	@RequestMapping("subaccountList")
	@ResponseBody
	public String subaccountList(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand, String fuser) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<User> user = subaccountService.getAll(search, offset, limit, sort,
					order, filter, brand, fuser);
			Integer count = subaccountService.getCount(search, filter, brand, fuser);
			jsonObject.put("total", count);
			jsonObject.put("rows", user);
		} catch (Exception ex) {
			log.error("subaccountList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 查询子账号机型
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("modelSelected")
	@ResponseBody
	public String modelSelected(Integer uid) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<String> model = subaccountService.getModelSelectBySub(uid);
			jsonObject.put("select_array", model.toArray());
		} catch (Exception ex) {
			log.error("modelSelected error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 查询权限列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("permissionList")
	@ResponseBody
	public List<Permission> permissionlist(Integer uid) throws IOException {
		List<Permission> permlist = new ArrayList<>();
		try {
			permlist = subaccountService.getAllPermByUID(uid);
		} catch (Exception ex) {
			log.error("permissionlist error : " + ex);
		}
		return permlist;
	}
	
	/**
	 * 查询子账号权限
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("perSelected")
	@ResponseBody
	public List<RolePer> perSelected(Integer uid) throws IOException {
		List<RolePer> rolePers = new ArrayList<>();
		try {
			rolePers = subaccountService.getPerSelectBySub(uid);
		} catch (Exception ex) {
			log.error("perSelected error : " + ex);
		}
		return rolePers;
	}
	
	/**
	 * 分配机型和权限
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "assignPer", method = RequestMethod.POST)
	@ResponseBody
	public String assignPer(@ModelAttribute("uid") Integer uid,
			@ModelAttribute("model") String model,
			@ModelAttribute("per") String per) throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			// 先清空子账号机型关联表
			subaccountService.deleteModelBySub(uid);
			if (StringUtils.isNotEmpty(model)){
				String[] model_array = model.split("#");
				if (model_array.length != 0){
					List<SubModel> list = new ArrayList<>();
					for(String model_val : model_array){
						list.add(new SubModel(uid, model_val));
					}
					subaccountService.insertSubModel(list);
				}
			}
			
			// 先清空子账号权限关联表
			subaccountService.deletePerBySub(uid);
			if (StringUtils.isNotEmpty(per)){
				String[] per_array = per.split("#");
				if (per_array.length != 0){
					List<SubPer> list = new ArrayList<>();
					for(String per_val : per_array){
						list.add(new SubPer(uid, Integer.parseInt(per_val)));
					}
					subaccountService.insertSubPer(list);
				}
			}
			
			// 设置返回结果
			setMessage(1, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("assignPer error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@ModelAttribute("user") User user) throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			if (userService.getUserByUserName(user.getName()) != 0) {// 验证用户名唯一性
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else if (userService.getUserByEmail(user.getEmail()) != 0) {// 验证邮箱唯一性
				jsonObject.put("message", "emailnotunique");
				jsonObject.put("status", "emailnotunique");
			} else {
				String init_pass = SecurityUtil.getSecurityCode(8);
				user.setPwd(init_pass);// 初始化密码
				if (!StringUtils.isEmpty(user.getPwd())) {// md加密
					user.setPwd(SecurityUtil.md5(user.getPwd()));
				}
				
				JavaMailSender jm = new JavaMailSender();
				boolean result = jm.sendMailContent(new String[] { user
						.getEmail() }, null, SecurityUtil.mailSubject,
						SecurityUtil.getInitPassContent(user.getName(),
								init_pass));
				log.info("addUser>>email=" + user.getEmail()
						+ ",init_pass=" + init_pass + ",sendMail result = "
						+ result);
				
				if (result){
					int res = subaccountService.addUser(user);
					logMsg(new LogData(getUser().getId(), getUser().getName(),
							getIp(), "新增用户: " + user.toString(), "add user : "
									+ user.toString(), 2, res));
					
					if (res != 0) {
						if (StringUtils.isNotEmpty(user.getModel())){
							String[] model_array = user.getModel().split("#");
							if (model_array.length != 0){
								List<SubModel> list = new ArrayList<>();
								for(String model_val : model_array){
									list.add(new SubModel(user.getId(), model_val));
								}
								subaccountService.insertSubModel(list);
							}
						}
						if (StringUtils.isNotEmpty(user.getPer())){
							String[] per_array = user.getPer().split("#");
							if (per_array.length != 0){
								List<SubPer> list = new ArrayList<>();
								for(String per_val : per_array){
									list.add(new SubPer(user.getId(), Integer.parseInt(per_val)));
								}
								subaccountService.insertSubPer(list);
							}
						}
					}
					
					// 设置返回结果
					setMessage(res, jsonObject);
				} else {
					jsonObject.put("message", "emailformat");
					jsonObject.put("status", "emailformat");
				}
				
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("addUser error : " + ex);
		}
		return jsonObject.toString();
	}

}
