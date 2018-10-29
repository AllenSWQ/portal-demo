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
import com.portal.model.Role;
import com.portal.model.RolePer;
import com.portal.service.IRoleService;

@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
	private static Log log = LogFactory.getLog(RoleController.class);

	@Resource
	private IRoleService roleService;

	HttpSession session;

	@RequestMapping("role")
	public String role() {
		return "role/role";
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("roleList")
	@ResponseBody
	public List<Role> roleList() throws IOException {
		List<Role> role = new ArrayList<>();
		try {
			role = roleService.getRoleList();
		} catch (Exception ex) {
			log.error("roleList error : " + ex);
		}
		return role;
	}

	@RequestMapping("rolepermissionlist")
	@ResponseBody
	public List<RolePer> rolepermissionlist(String roleid) throws IOException {
		List<RolePer> rolePers = new ArrayList<>();
		try {
			rolePers = roleService.getrolePers(roleid);
		} catch (Exception ex) {
			log.error("rolepermissionlist error : " + ex);
		}
		return rolePers;
	}

	@RequestMapping("addrolePer")
	@ResponseBody
	public int addrolePer(String rid, String pid) throws IOException {
		int res = 0;
		try {
			res = roleService.addrolePer(rid, pid);
		} catch (Exception ex) {
			log.error("addrolePer error : " + ex);
		}
		return res;
	}

	@RequestMapping("delrolePer")
	@ResponseBody
	public int delrolePer(String rid, String pid) throws IOException {
		int res = 0;
		try {
			res = roleService.delrolePer(rid, pid);
			
			if (res != 0){// 同时删除子账号权限
				roleService.deleteSubPerByRidPid(rid, pid);
			}
			
		} catch (Exception ex) {
			log.error("delrolePer error : " + ex);
		}
		return res;
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	@ResponseBody
	public int addRole(@ModelAttribute("role") Role role) throws IOException {
		int res = 0;
		try {
			// 验证角色名唯一性
			if (roleService.getRoleByRoleName(role.getName()) != 0) {
				res = 2;
			} else {
				res = roleService.add(role);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "新增角色 : " + role.toString(), "add role : "
								+ role.toString(), 2, res));
			}
		} catch (Exception ex) {
			log.error("addRole error : " + ex);
		}
		return res;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editRole", method = RequestMethod.POST)
	@ResponseBody
	public int editRole(@ModelAttribute("role") Role role) throws IOException {
		int res = 0;
		try {
			// 验证角色名唯一性
			if (roleService.getRoleByRoleName(role.getName()) != 0) {
				res = 2;
			} else {
				res = roleService.updateByPrimaryKeySelective(role);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "修改角色: " + role.toString(), "edit role : "
								+ role.toString(), 3, res));
			}
		} catch (Exception ex) {
			log.error("editRole error : " + ex);
		}
		return res;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delRole", method = RequestMethod.POST)
	@ResponseBody
	public int delRole(@RequestParam(value = "ids") List<String> ids)
			throws IOException {
		int res = 0;
		try {
			res = roleService.delete(ids);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除角色: roleid=" + ids, "delete role : roleid=" + ids, 4,
					res));
			
			if (res != 0) {
				for (String roleid : ids) {
					roleService.deleteSubPerByRid(roleid);// 删除子账号权限数据
					roleService.delPerRoleByRoleId(roleid);// 删除角色权限关联表数据
					roleService.delUserRoleByRoleId(roleid);// 删除用户角色关联表数据
				}
			}
		} catch (Exception ex) {
			log.error("delRole error : " + ex);
		}
		return res;
	}

}
