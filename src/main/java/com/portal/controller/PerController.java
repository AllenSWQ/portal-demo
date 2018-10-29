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
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.RolePer;
import com.portal.service.IPerService;

@Controller
@RequestMapping("/perController")
public class PerController extends BaseController {
	private static Log log = LogFactory.getLog(PerController.class);

	@Resource
	private IPerService perService;

	HttpSession session;

	@RequestMapping("per")
	public String per() {
		return "per/per";
	}

	/**
	 * 角色权限列表
	 * 
	 * @param userid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("rolepermissionlist")
	@ResponseBody
	public List<RolePer> rolepermissionlist(String userid) throws IOException {
		List<RolePer> rolePers = new ArrayList<>();
		try {
			rolePers = perService.getrolePers(userid);
		} catch (Exception ex) {
			log.error("rolepermissionlist error : " + ex);
		}
		return rolePers;
	}

	/**
	 * 新增权限
	 * 
	 * @param per
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addPer", method = RequestMethod.POST)
	@ResponseBody
	public int addPer(@ModelAttribute("Permission") Permission per)
			throws IOException {
		int res = 0;
		try {
			if (per.getFlevel() != 0) {
				perService.setPerState(per.getFlevel(), 1);
			}
			res = perService.addPer(per);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"新增权限 : " + per.toString(), "add per : " + per.toString(),
					2, res));
		} catch (Exception ex) {
			log.error("addPer error : " + ex);
		}
		return res;
	}

	/**
	 * 修改权限
	 * 
	 * @param per
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editPer", method = RequestMethod.POST)
	@ResponseBody
	public int editPer(@ModelAttribute("Permission") Permission per)
			throws IOException {
		int res = 0;
		try {
			res = perService.editPer(per);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"修改权限 : " + per.toString(), "edit per : " + per.toString(),
					3, res));
		} catch (Exception ex) {
			log.error("editPer error : " + ex);
		}
		return res;
	}

	/**
	 * 删除权限
	 * 
	 * @param rid
	 * @param flevel
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delPer", method = RequestMethod.POST)
	@ResponseBody
	public int delPer(String rid, String flevel) throws IOException {
		int getpers = 0;
		int res = 0;
		try {
			getpers = perService.getperstate(flevel);
			if (getpers == 1) {
				perService.setPerState(Integer.parseInt(flevel), 0);
			}
			perService.delPerRole(rid);
			res = perService.delPer(rid);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除权限 : perid=" + rid, "delete per : perid=" + rid, 4, res));
		} catch (Exception ex) {
			log.error("editPer error : " + ex);
		}
		return res;
	}

}
