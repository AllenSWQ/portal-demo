package com.portal.controller;

import java.io.IOException;
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

import com.portal.model.Brand;
import com.portal.model.LogData;
import com.portal.model.User;
import com.portal.service.IBrandService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/brandController")
public class BrandController extends BaseController {
	private static Log log = LogFactory.getLog(BrandController.class);

	@Resource
	private IBrandService brandService;

	HttpSession session;

	@RequestMapping("brand")
	public String brand() {
		return "brand/brand";
	}

	/**
	 * 厂商下拉框
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("brandSelect")
	@ResponseBody
	public String brandSelect() throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			List<Brand> model = brandService.getSelect(m_user_id);
			jsonObject.put("rows", model);
		} catch (Exception ex) {
			log.error("brandSelect error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 厂商列表
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
	@RequestMapping("brandList")
	@ResponseBody
	public String brandList(String search, Integer offset, Integer limit,
			String sort, String order, String filter) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			// 本地需转码
			/*if (StringUtils.isNotEmpty(search)){
				search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
			}*/
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			List<Brand> list = brandService.getAll(search, offset, limit, sort,
					order, filter, m_user_id);
			Integer count = brandService.getCount(search, filter, m_user_id);
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询厂商管理", "search Brand Mngt", 1, 1));
		} catch (Exception ex) {
			log.error("brandList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 新增厂商
	 * 
	 * @param brand
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addBrand", method = RequestMethod.POST)
	@ResponseBody
	public String addBrand(@ModelAttribute("brand") Brand brand) throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			if (brandService.getBrandByCode(brand.getBrand_en(), brand.getBrand_cn()) != 0) {// 验证唯一性
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else {
				int res = brandService.addBrand(brand);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "新增厂商: " + brand.toString(), "add brand : "
								+ brand.toString(), 2, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("addBrand error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 修改厂商
	 * 
	 * @param brand
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editBrand", method = RequestMethod.POST)
	@ResponseBody
	public String editBrand(@ModelAttribute("brand") Brand brand)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			if (brandService.getBrandByCode(brand.getBrand_en(), brand.getBrand_en()) > 1) {// 验证唯一性
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else {
				int res = brandService.editBrand(brand);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "修改厂商: " + brand.toString(), "edit brand : "
								+ brand.toString(), 3, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editBrand error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 删除厂商
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delBrand", method = RequestMethod.POST)
	@ResponseBody
	public String delBrand(@RequestParam(value = "id") Integer id)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = brandService.deleteBrand(id);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除厂商: id=" + id, "delete brand : id=" + id, 4,
					res));
			
			if (res != 0){// 删除厂商，更新该厂商用户state=3
				brandService.updateUserState(id);
				brandService.deluroleByBrandId(id);
			}
			
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("delBrand error : " + ex);
		}
		return jsonObject.toString();
	}

}
