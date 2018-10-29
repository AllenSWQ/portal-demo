package com.portal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.constant.Constant;
import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserBrand;
import com.portal.redis.RedisClient;
import com.portal.service.IUserService;
import com.portal.util.DateUtil;
import com.portal.util.JavaMailSender;
import com.portal.util.SecurityUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {
	private static Log log = LogFactory.getLog(UserController.class);

	@Resource
	private IUserService userService;

	HttpSession session;

	@RequestMapping("index")
	public String index() {
		return "user/index";
	}

	@RequestMapping("index_v1")
	public String index_v1() {
		return "user/index_v1";
	}

	@RequestMapping("sensetimeLogin")
	public String sensetimeLogin() {
		return "user/sensetimeLogin";
	}
	
	@RequestMapping("login")
	public String login() {
		return "user/login";
	}

	@RequestMapping("user")
	public String user() {
		return "user/user";
	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(@ModelAttribute("user") User user,
			HttpServletRequest req) throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();

		String lang = StringUtils.isEmpty(user.getLang()) ? "cn" : user
				.getLang();

		// 验证码
		String checkcode = (String) session.getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(user.getVerficode())) {
			jsonObject.put("message", "codeerror");
			jsonObject.put("status", "codeerror");
			return jsonObject.toString();
		}

		if (!StringUtils.isEmpty(user.getPwd())) {// md加密
			user.setPwd(SecurityUtil.md5(user.getPwd()));
		}

		try {
			user = userService.Login(user);
			if (user != null) {
				
				// 判断用户是否未激活
				if (user.getState() == 0){
					jsonObject.put("message", user.getName());
					jsonObject.put("status", "notactive");
					return jsonObject.toJSONString();
				}
				
				// 判断用户是否冻结
				if (user.getState() == 2){
					jsonObject.put("message", user.getName());
					jsonObject.put("status", "freezed");
					return jsonObject.toJSONString();
				}
				
				// 若为子账号判断母账号是否冻结
				if (user.getFuser() != 1){
					User fuser = userService.getUserById(user.getFuser());
					if (fuser == null || fuser.getState() == 2 || fuser.getState() == 3){
						jsonObject.put("message", user.getName());
						jsonObject.put("status", "freezed");
						return jsonObject.toJSONString();
					}
				}
				
				log.info("user login in = " + user.getName());
				session.setAttribute("cUserSession", user);
				session.setAttribute("lang", lang);
				jsonObject.put("message", "success");
				jsonObject.put("status", "success");
				logMsg(new LogData(user.getId(), user.getName(), getIp(),
						user.getName() + " login in", user.getName()
								+ " login in", 0, 1));
			} else {
				jsonObject.put("message", "error");
				jsonObject.put("status", "error");
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("doLogin error : " + ex);
		}
		return jsonObject.toString();
	}

	/**
	 * 账户列表
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
	@RequestMapping("userList")
	@ResponseBody
	public String userList(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand, String fuser) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<User> user = userService.getAll(search, offset, limit, sort,
					order, filter, brand, fuser);
			Integer count = userService.getCount(search, filter, brand, fuser);
			jsonObject.put("total", count);
			jsonObject.put("rows", user);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询账户管理", "search User Mngt", 1, 1));
		} catch (Exception ex) {
			log.error("userList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
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
					if (user.getType() == 0 || user.getType() == 2){// 管理员和多厂商
						user.setBrand_id(0);
					}
					int res = userService.addUser(user);
					if (user.getType() == 2){// 多厂商初始化用户厂商关联表
						if (!StringUtils.isEmpty(user.getBrand_array())){
							String[] brand_array = user.getBrand_array().split("#");
							if (brand_array.length != 0){
								List<UserBrand> list = new ArrayList<>();
								for(String val : brand_array){
									list.add(new UserBrand(user.getId(), Integer.parseInt(val)));
								}
								userService.insertUserBrand(list);
							}
						}
					}
					logMsg(new LogData(getUser().getId(), getUser().getName(),
							getIp(), "新增用户: " + user.toString(), "add user : "
									+ user.toString(), 2, res));
					
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

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editUser", method = RequestMethod.POST)
	@ResponseBody
	public String editUser(@ModelAttribute("user") User user)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			// 用户名没改 不用验证唯一性
			User oldUser = userService.getUserById(user.getId());

			// 验证用户名唯一性
			if (!oldUser.getName().equals(oldUser.getName())
					&& userService.getUserByUserName(user.getName()) != 0) {
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else {
				int res = userService.updateByPrimaryKeySelective(user);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "修改用户: " + user.toString(), "edit user : "
								+ user.toString(), 3, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editUser error : " + ex);
		}
		return jsonObject.toString();
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editUserPass", method = RequestMethod.POST)
	@ResponseBody
	public String editUserPass(@ModelAttribute("user") User user)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		if (!StringUtils.isEmpty(user.getPwd())) {// md加密
			user.setPwd(SecurityUtil.md5(user.getPwd()));
		}
		try {
			int res = userService.updateByPrimaryKeySelective(user);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改用户密码: userid=" + user.getId(),
					"edit user password : userid=" + user.getId(), 3, res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editUserPass error : " + ex);
		}
		return jsonObject.toString();
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delUser", method = RequestMethod.POST)
	@ResponseBody
	public String delUser(@RequestParam(value = "ids") List<String> ids)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			for (String userid : ids) {
				userService.delurole(userid);
			}
			int res = userService.delete(ids);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除用户: userid=" + ids, "delete user : userid=" + ids, 4,
					res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("delUser error : " + ex);
		}
		return jsonObject.toString();
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("roleList")
	@ResponseBody
	public String roleList(String search, Integer offset, Integer limit,
			String sort, String order, String filter) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<Role> userRole = userService.getAllRole(search, offset, limit,
					sort, order, filter);
			Integer count = userService.getRoleCount(search, filter);
			jsonObject.put("total", count);
			jsonObject.put("rows", userRole);
		} catch (Exception ex) {
			log.error("roleList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 修改用户角色
	 * 
	 * @param userid
	 * @param roleid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("editUserRole")
	@ResponseBody
	public String editUserRole(String userid, String roleid) throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			userService.delurole(userid);
			int res = userService.addurole(userid, roleid);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改用户角色: userid=" + userid + ",roleid=" + roleid,
					"modify user role : userid=" + userid + ",roleid=" + roleid, 3,
					res));
			
			if (res != 0){// 同时更新子账号的权限
				userService.deleteSubPerByFuserRid(userid, roleid);
			}
			
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editUserRole error : " + ex);
		}
		return jsonObject.toString();
	}

	@RequestMapping("getRoleUser")
	@ResponseBody
	public String getRoleUser(String roleid) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<User> userRole = userService.getRoleUser(roleid);
			if (userRole.size() == 0)
				return "";
			jsonObject.put("userinfo", userRole);
		} catch (Exception ex) {
			log.error("getRoleUser error : " + ex);
		}
		return jsonObject.toString();
	}

	@RequestMapping("useRole")
	@ResponseBody
	public String useRole(String userid) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<Role> userRole = userService.isrole(userid);
			jsonObject.put("total", userRole);
			jsonObject.put("rows", userRole.size());
		} catch (Exception ex) {
			log.error("useRole error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest req) {
		session = req.getSession();
		session.invalidate();
		return "user/login";
	}
	
	@RequestMapping("sensetimeLoginOut")
	public String sensetimeLoginOut(HttpServletRequest req) {
		session = req.getSession();
		session.invalidate();
		return "user/sensetimeLogin";
	}

	/**
	 * 查询权限列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("permissionList")
	@ResponseBody
	public List<Permission> permissionlist() throws IOException {
		List<Permission> userRole = new ArrayList<>();
		try {
			userRole = userService.getAllPerm();
		} catch (Exception ex) {
			log.error("permissionlist error : " + ex);
		}
		return userRole;
	}

	@RequestMapping(value = "/testRestful/{id}", method = { RequestMethod.GET })
	public String testRestfulGet(Integer id) {
		System.out.println("The value which the restful style url is GET :"
				+ id);
		return "user/success";
	}

	@RequestMapping(value = "/testRestful", method = { RequestMethod.POST })
	public String testRestfulPost(User user, HttpServletRequest req,
			HttpServletResponse resp) {
		System.out.println("The value which the restful style url is POST ");
		System.out.println(user.getName());
		return "user/success";
	}

	@RequestMapping(value = "/testRestful/{id}", method = { RequestMethod.DELETE })
	public String testRestfulDelete(Integer id) {
		System.out.println("The value which the restful style url is DELETE :"
				+ id);
		return "user/success";
	}

	@RequestMapping(value = "/testRestful/{id}", method = { RequestMethod.PUT })
	public String testRestfulPut(Integer id) {
		System.out.println("The value which the restful style url is PUT :"
				+ id);
		return "user/success";
	}

	/**
	 * 获取用户菜单权限
	 * 
	 * @param userid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getUserMenu")
	@ResponseBody
	public List<Permission> getUserMenu(String userid) throws IOException {
		List<Permission> menuList = new ArrayList<>();
		
		try {
			User user = getUser();
			if (user.getFuser() != 1){// 子账号
				menuList = userService.getSubUserMenu(userid);
			} else {
				menuList = userService.getUserMenu(userid);
			}
		} catch (Exception ex) {
			log.error("getUserMenu error : " + ex);
		}
		return menuList;
	}
	
	/**
	 * 更改用户状态
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updateUserState", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserState(@ModelAttribute("user") User user)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = userService.updateByPrimaryKeySelective(user);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改用户状态: id = " + user.getId() + ", state = "
							+ user.getState(), "update user state: id = "
							+ user.getId() + ", state = " + user.getState(), 3,
					res));
			
			if (res != 0){
				if (user.getState() == 3){// 删除用户: 1-清除用户角色表内数据
					userService.delurole(String.valueOf(user.getId()));
					// 同时更改子账号状态
					userService.updateSubaccountState(user.getId(), user.getState());
				}
			}
			
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("updateUserState error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 激活用户
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "activeUser", method = RequestMethod.POST)
	@ResponseBody
	public String activeUser(@ModelAttribute("user") User user,
			HttpServletRequest req) throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();

		if (!StringUtils.isEmpty(user.getPwd())) {// md加密
			user.setPwd(SecurityUtil.md5(user.getPwd()));
		}
		
		String newpwd = user.getNewpwd();

		try {
			user = userService.Login(user);
			if (user != null && !StringUtils.isEmpty(newpwd)) {
				
				User newUser = new User();
				newUser.setId(user.getId());
				newUser.setPwd(SecurityUtil.md5(newpwd));
				newUser.setState(1);
				
				int res = userService.updateByPrimaryKeySelective(newUser);
				logMsg(new LogData(user.getId(), user.getName(), getIp(),
						user.getName() + " activate", user.getName()
								+ " activate", 5, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			} else {
				jsonObject.put("message", "error");
				jsonObject.put("status", "error");
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("activeUser error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 邮箱验证
	 * 
	 * @param email
	 * @param verficode
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "emailValidate", method = RequestMethod.POST)
	@ResponseBody
	public String emailValidate(@ModelAttribute("email") String email,
			@ModelAttribute("verficode") String verficode,
			HttpServletRequest req) throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();

		try {
			// 验证码
			String checkcode = (String) session.getAttribute("checkcode");
			if (!checkcode.equalsIgnoreCase(verficode)) {
				jsonObject.put("message", "codeerror");
				jsonObject.put("status", "codeerror");
				return jsonObject.toString();
			}
			
			User user = userService.getUserByEmailForgetPass(email);
			
			// 邮箱
			if (user == null){
				jsonObject.put("message", "emailNotExist");
				jsonObject.put("status", "emailNotExist");
				return jsonObject.toString();
			} else if (user.getState() == 2){
				jsonObject.put("message", user.getName());
				jsonObject.put("status", "freezed");
				return jsonObject.toJSONString();
			} else if (user.getState() == 0) {
				jsonObject.put("message", user.getName());
				jsonObject.put("status", "notactive");
				return jsonObject.toJSONString();
			}
			
			// 设置返回结果
			setMessage(1, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("emailValidate error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "sendSecurityCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendSecurityCode(@ModelAttribute("email") String email, HttpServletRequest req)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();

		try {
			String key = email + "_" + DateUtil.getDate(0) + Constant.SECURITY_CODE;
			String sentCount = (String) RedisClient.getInstance().get(key);
			if (!StringUtils.isEmpty(sentCount) && Integer.parseInt(sentCount) >= 10) {// 一天同一个邮箱不能发送超过10次
				log.info("sendSecurityCode>>email " + email + " sent over 10 times");
				jsonObject.put("message", "sendover");
				jsonObject.put("status", "sendover");
				return jsonObject.toString();
			}
			
			JavaMailSender jm = new JavaMailSender();
			String security_code = SecurityUtil.getSecurityCode(8);
			boolean result = jm.sendMailContent(new String[] { email }, null,
					SecurityUtil.securitySubject,
					SecurityUtil.getSecurityCodeContent(email, security_code));
			session.setAttribute("security_code", security_code);
			log.info("sendSecurityCode>>email=" + email + ",security_code="
					+ security_code + ",sendMail result = " + result);
			
			if(result){
				RedisClient.getInstance().incr(key, 1);
			}
			
			// 设置返回结果
			setMessage(result, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("sendSecurityCode error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 安全验证
	 * 
	 * @param verficode
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "securityValidate", method = RequestMethod.POST)
	@ResponseBody
	public String securityValidate(@ModelAttribute("verficode") String verficode,
			HttpServletRequest req) throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();

		try {
			// 验证码
			String security_code = (String) session.getAttribute("security_code");
			if (!security_code.equalsIgnoreCase(verficode)) {
				jsonObject.put("message", "codeerror");
				jsonObject.put("status", "codeerror");
				return jsonObject.toString();
			}
			
			// 设置返回结果
			setMessage(1, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("securityValidate error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 重置密码
	 * 
	 * @param email
	 * @param pwd
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public String resetPassword(@ModelAttribute("email") String email,
			@ModelAttribute("pwd") String pwd) throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			int count = userService.getUserByEmail(email);
			int res = 0;
			if (count == 1) {// 唯一才重置密码
				res = userService.resetPassByEmail(email, SecurityUtil.md5(pwd));
			}
			
			logMsg(new LogData(0, email, getIp(), "重置密码", "reset password", 3, res));
			
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("resetPassword error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 修改信息
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public String updateInfo(@ModelAttribute("user") User user, HttpServletRequest req)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		session = req.getSession();
		
		try {
			int res = userService.updateByPrimaryKeySelective(user);
			logMsg(new LogData(getUser().getId(), getUser().getName(),
					getIp(), "修改信息: " + user.toString(), "modify Info : "
							+ user.toString(), 3, res));
			if(res == 1){// 修改成功更新session数据
				User info = (User) session.getAttribute("cUserSession");
				info.setContact(user.getContact());
				info.setPhone(user.getPhone());
				session.setAttribute("cUserSession", info);
			}
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改信息", "modify information", 3, res));
			
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("updateInfo error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updatePass", method = RequestMethod.POST)
	@ResponseBody
	public String updatePass(@ModelAttribute("user") User user) throws IOException {
		JSONObject jsonObject = new JSONObject();

		if (!StringUtils.isEmpty(user.getPwd())) {// md加密
			user.setPwd(SecurityUtil.md5(user.getPwd()));
		}
		
		String newpwd = user.getNewpwd();

		try {
			user = userService.Login(user);
			if (user != null && !StringUtils.isEmpty(newpwd)) {
				
				User newUser = new User();
				newUser.setId(user.getId());
				newUser.setPwd(SecurityUtil.md5(newpwd));
				
				int res = userService.updateByPrimaryKeySelective(newUser);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "修改密码: " + user.toString(), "modify password : "
								+ user.toString(), 3, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			} else {
				jsonObject.put("message", "error");
				jsonObject.put("status", "error");
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("updatePass error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 查询账号厂商数据
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("brandSelected")
	@ResponseBody
	public String brandSelected(Integer uid) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<String> brand = userService.getUserBrandSelectByID(uid);
			jsonObject.put("select_array", brand.toArray());
		} catch (Exception ex) {
			log.error("brandSelected error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 分配厂商
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "modifyUserBrand", method = RequestMethod.POST)
	@ResponseBody
	public String modifyUserBrand(@ModelAttribute("uid") Integer uid,
			@ModelAttribute("brand_array") String brand_array) throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			// 先清空子账号机型关联表
			userService.deleteUserBrandByID(uid);
			if (!StringUtils.isEmpty(brand_array)){
				String[] brand = brand_array.split("#");
				if (brand.length != 0){
					List<UserBrand> list = new ArrayList<>();
					for(String val : brand){
						list.add(new UserBrand(uid, Integer.parseInt(val)));
					}
					userService.insertUserBrand(list);
				}
			}
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改用户厂商: userid=" + uid + ",brandid=" + brand_array,
					"modify user brand: userid=" + uid + ",brandid=" + brand_array, 3,
					1));
			
			// 设置返回结果
			setMessage(1, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("modifyUserBrand error : " + ex);
		}
		return jsonObject.toString();
	}

}
