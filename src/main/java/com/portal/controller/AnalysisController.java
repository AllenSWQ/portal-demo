package com.portal.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.constant.Constant;
import com.portal.model.LogData;
import com.portal.model.StChannel;
import com.portal.model.StDay;
import com.portal.model.StDetail;
import com.portal.model.User;
import com.portal.service.IAnalysisService;
import com.portal.util.DataUtil;
import com.portal.util.DateUtil;
import com.portal.util.ExcelUtil;
import com.portal.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/analysisController")
public class AnalysisController extends BaseController {
	private static Log log = LogFactory.getLog(AnalysisController.class);

	@Resource
	private IAnalysisService analysisService;

	HttpSession session;

	@RequestMapping("dashboard")
	public String dashboard() {
		return "analysis/dashboard";
	}

	@RequestMapping("active")
	public String active() {
		return "analysis/active";
	}

	@RequestMapping("model")
	public String model() {
		return "analysis/model";
	}

	@RequestMapping("detail")
	public String detail() {
		return "analysis/detail";
	}

	@RequestMapping("brand")
	public String brand() {
		return "analysis/brand";
	}

	/**
	 * 用户新增-表格
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
	@RequestMapping("activeDataTable")
	@ResponseBody
	public String activeDataTable(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StDay> list = new ArrayList<>();
			Integer count = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getActiveDataTotal(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getActiveDataList(search, offset, limit,
					sort, order, filter, brand_id, model, start_date, end_date,
					total, user_id, m_user_id);
			count = analysisService.getActiveDataCount(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);

			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询用户新增", "search Active Device", 1, 1));
		} catch (Exception ex) {
			log.error("activeDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 日期明细-表格
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
	@RequestMapping("dateDetailDataTable")
	@ResponseBody
	public String dateDetailDataTable(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StDay> list = new ArrayList<>();
			Integer count = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getActiveDataTotal(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getActiveDataList(search, offset, limit,
					sort, order, filter, brand_id, model, start_date, end_date,
					total, user_id, m_user_id);
			count = analysisService.getActiveDataCount(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);

			jsonObject.put("total", count);
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("dateDetailDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 用户新增-走势图
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
	@RequestMapping("activeDataTread")
	@ResponseBody
	public String activeDataTread(String sort, String order, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			Integer count = 0;
			List<StDay> list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getActiveDataTotal(null, null, brand_id,
					model, start_date, end_date, user_id, m_user_id);
			count = analysisService.getActiveDataCount(null, null, brand_id,
					model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getActiveDataList(null, 0, count, sort,
					order, null, brand_id, model, start_date, end_date, total,
					user_id, m_user_id);

			if (StringUtils.isNotEmpty(start_date)
					&& "2018-01-01".equals(start_date)) {
				jsonObject.put("rows", DataUtil.filterDataYM(list));
			} else {
				jsonObject.put("rows", DataUtil.filterData(list));
			}
		} catch (Exception ex) {
			log.error("activeDataTread error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 机型走势-表格
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
	@RequestMapping("modelDataTable")
	@ResponseBody
	public String modelDataTable(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StDay> list = new ArrayList<>();
			Integer count = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			total = analysisService.getModelDataTableTotal(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getModelDataTable(search, offset, limit,
					sort, order, filter, brand_id, model, start_date,
					end_date, total, user_id, m_user_id);
			count = analysisService.getModelDataTableCount(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询机型走势", "search Model Trend", 1, 1));
		} catch (Exception ex) {
			log.error("modelDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 机型走势-时间段内激活数
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
	@RequestMapping("modelDataTimeslot")
	@ResponseBody
	public String modelDataTimeslot(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer timeslot = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			timeslot = analysisService.getModelDataTableTotal(search,
					filter, brand_id, model, start_date, end_date, user_id, m_user_id);
			
			jsonObject.put("timeslot", timeslot);
		} catch (Exception ex) {
			log.error("modelDataTimeslot error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 机型走势-走势图
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
	@RequestMapping("modelDataTrend")
	@ResponseBody
	public String modelDataTrend(String sort, String order, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			// Integer count = 0;
			List<StDay> model_list = new ArrayList<>();
			List<StDay> data_list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			total = analysisService.getModelDataTableTotal(null, null,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			model_list = analysisService.getModelDataTable(null, 0, 10,
					sort, order, null, brand_id, model, start_date,
					end_date, total, user_id, m_user_id);
			data_list = analysisService.getModelDataTrend(brand_id, model,
					start_date, end_date, user_id, m_user_id);
			

			jsonObject.put("model_list", model_list);
			if (StringUtils.isNotEmpty(start_date)
					&& "2018-01-01".equals(start_date)) {
				jsonObject.put("rows",
						DataUtil.filterMultipleDataYM(data_list, model_list));
			} else {
				jsonObject.put("rows",
						DataUtil.filterMultipleData(data_list, model_list));
			}

		} catch (Exception ex) {
			log.error("modelDataTrend error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 用户明细-表格
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
	@RequestMapping("detailDataTable")
	@ResponseBody
	public String detailDataTable(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String model, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StDay> list = new ArrayList<>();
			Integer count = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			total = analysisService.getModelDataTableTotal(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getModelDataTable(search, offset, limit,
					sort, order, filter, brand_id, model, start_date,
					end_date, total, user_id, m_user_id);
			count = analysisService.getModelDataTableCount(search, filter,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询用户明细", "search Device Detail", 1, 1));
		} catch (Exception ex) {
			log.error("detailDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 渠道明细-表格
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
	@RequestMapping("channelDataTable")
	@ResponseBody
	public String channelDataTable(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = analysisService.getChannelDataTotal(search, filter,
					brand_id, model);

			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			List<StChannel> list = analysisService.getChannelDataList(search,
					offset, limit, sort, order, filter, brand_id, model, total,
					m_user_id);
			Integer count = analysisService.getChannelDataCount(search, filter,
					brand_id, model);
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("channelDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 渠道明细-饼状图
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
	@RequestMapping("channelDataPie")
	@ResponseBody
	public String channelDataPie(String sort, String order, String brand_id,
			String model) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = analysisService.getChannelDataTotal(null, null,
					brand_id, model);
			Integer count = analysisService.getChannelDataCount(null, null,
					brand_id, model);

			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			List<StChannel> list = analysisService
					.getChannelDataList(null, 0, count, sort, order, null,
							brand_id, model, total, m_user_id);
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("channelDataPie error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 厂商分布-表格
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
	@RequestMapping("brandDataTable")
	@ResponseBody
	public String brandDataTable(Integer offset, Integer limit, String sort,
			String order, String brand_id, String start_date, String end_date)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			Integer total = analysisService.getBrandDataTotal(null, null,
					brand_id, start_date, end_date, m_user_id);
			List<StChannel> list = analysisService.getBrandDataList(offset,
					limit, sort, order, total, brand_id, start_date, end_date, m_user_id);
			Integer count = analysisService.getBrandDataCount(null, null,
					brand_id, start_date, end_date, m_user_id);
			
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询厂商分布", "search Brand Ratio", 1, 1));
		} catch (Exception ex) {
			log.error("brandDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 厂商分布-饼状图
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("brandDataPie")
	@ResponseBody
	public String brandDataPie(String sort, String order, String brand_id,
			String start_date, String end_date) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			Integer total = analysisService.getBrandDataTotal(null, null,
					brand_id, start_date, end_date, m_user_id);
			Integer count = analysisService.getBrandDataCount(null, null,
					brand_id, start_date, end_date, m_user_id);
			List<StChannel> list = analysisService.getBrandDataList(0, count,
					sort, order, total, brand_id, start_date, end_date, m_user_id);
			
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("brandDataPie error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 厂商走势-走势图
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
	@RequestMapping("brandDataTrend")
	@ResponseBody
	public String brandDataTrend(String sort, String order, String brand_id,
			String start_date, String end_date) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StChannel> brand_list = new ArrayList<>();
			List<StDay> data_list = new ArrayList<>();

			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			total = analysisService.getBrandDataTotal(null, null, brand_id,
					start_date, end_date, m_user_id);
			brand_list = analysisService.getBrandDataList(0, 10, sort, order,
					total, brand_id, start_date, end_date, m_user_id);
			data_list = analysisService.getBrandDataTrend(brand_id, start_date,
					end_date, m_user_id);

			jsonObject.put("brand_list", brand_list);
			if (StringUtils.isNotEmpty(start_date)
					&& "2018-01-01".equals(start_date)) {
				jsonObject.put("rows", DataUtil.filterMultipleDataForBrandYM(
						data_list, brand_list));
			} else {
				jsonObject.put("rows", DataUtil.filterMultipleDataForBrand(
						data_list, brand_list));
			}

		} catch (Exception ex) {
			log.error("brandDataTrend error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 机型分布-表格
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
	@RequestMapping("brandModelDataTable")
	@ResponseBody
	public String brandModelDataTable(Integer offset, Integer limit,
			String sort, String order, String brand_id, String start_date,
			String end_date) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			List<StChannel> list = new ArrayList<>();
			Integer count = 0;

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getBrandModelDataTotal(brand_id, user_id,
					start_date, end_date, m_user_id);
			list = analysisService.getBrandModelDataList(offset, limit, sort,
					order, brand_id, total, user_id, start_date, end_date,
					m_user_id);
			count = analysisService.getBrandModelDataCount(brand_id, user_id,
					start_date, end_date, m_user_id);

			jsonObject.put("total", count);
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("brandModelDataTable error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 机型分布-饼状图
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
	@RequestMapping("brandModelDataPie")
	@ResponseBody
	public String brandModelDataPie(String sort, String order, String brand_id,
			String start_date, String end_date) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			Integer total = 0;
			Integer count = 0;
			List<StChannel> list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getBrandModelDataTotal(brand_id, user_id,
					start_date, end_date, m_user_id);
			count = analysisService.getBrandModelDataCount(brand_id, user_id,
					start_date, end_date, m_user_id);
			list = analysisService.getBrandModelDataList(0, count, sort, order,
					brand_id, total, user_id, start_date, end_date, m_user_id);

			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("brandModelDataPie error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}

	/**
	 * 导出明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportDetail", method = RequestMethod.GET)
	public void exportDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出明细数据", "export detail data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String brand_id = request.getParameter("brand_id");
			String model = request.getParameter("model");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet(ExcelUtil.export_detail_sheetname);
			String[] header = ExcelUtil.export_detail_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_detail_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}

			// 数据行
			List<StDetail> list = analysisService.getDetailDataList(brand_id,
					model);
			for (int i = 0; i < list.size(); i++) {
				StDetail data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
				}
				data_row.getCell(0).setCellValue(
						lang.equals("en") ? data.getBrand_en() : data
								.getBrand_cn());
				data_row.getCell(1).setCellValue(data.getModel());
				data_row.getCell(2).setCellValue(data.getImei());
				data_row.getCell(3).setCellValue(data.getImei2());
				data_row.getCell(4).setCellValue(data.getActive_time());
				data_row.getCell(5).setCellValue(data.getLast_time());
				data_row.getCell(6).setCellValue(data.getActive_version());
				data_row.getCell(7).setCellValue(data.getCurrent_version());
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_detail_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportDetail error : " + e);
		}
	}

	/**
	 * 导出激活数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportActiveData", method = RequestMethod.GET)
	public void exportActiveData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出激活数据", "export active data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String brand_id = request.getParameter("brand_id");
			String model = request.getParameter("model");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String sort = request.getParameter("sort");
			String order = request.getParameter("order");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet(ExcelUtil.export_active_sheetname);
			String[] header = ExcelUtil.export_active_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_active_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}

			// 数据行
			Integer total = 0;
			Integer count = 0;
			List<StDay> list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getActiveDataTotal(null, null, brand_id,
					model, start_date, end_date, user_id, m_user_id);
			count = analysisService.getActiveDataCount(null, null, brand_id,
					model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getActiveDataList(null, 0, count, sort,
					order, null, brand_id, model, start_date, end_date, total,
					user_id, m_user_id);

			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle ratio_style = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ratio_style.setDataFormat(df.getFormat("#,##0.00"));
			ratio_style.setAlignment(CellStyle.ALIGN_LEFT);

			for (int i = 0; i < list.size(); i++) {
				StDay data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
					data_row.getCell(j).setCellStyle(style);
				}
				data_row.getCell(0).setCellValue(data.getActive_date());
				data_row.getCell(1).setCellValue(data.getActive_num());
				data_row.getCell(2).setCellStyle(ratio_style);
				data_row.getCell(2).setCellValue(Double.parseDouble(data.getActive_ratio()));
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_active_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportActiveData error : " + e);
		}
	}

	/**
	 * 导出机型数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportModelData", method = RequestMethod.GET)
	public void exportModelData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出机型数据", "export model data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String brand_id = request.getParameter("brand_id");
			String model = request.getParameter("model");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String sort = request.getParameter("sort");
			String order = request.getParameter("order");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet(ExcelUtil.export_model_sheetname);
			String[] header = ExcelUtil.export_model_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_model_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}

			// 数据行
			Integer total = 0;
			Integer count = 0;
			List<StDay> list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			total = analysisService.getModelDataTableTotal(null, null,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			count = analysisService.getModelDataTableCount(null, null,
					brand_id, model, start_date, end_date, user_id, m_user_id);
			list = analysisService.getModelDataTable(null, 0, count, sort,
					order, null, brand_id, model, start_date, end_date,
					total, user_id, m_user_id);

			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle ratio_style = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ratio_style.setDataFormat(df.getFormat("#,##0.00"));
			ratio_style.setAlignment(CellStyle.ALIGN_LEFT);

			for (int i = 0; i < list.size(); i++) {
				StDay data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
					data_row.getCell(j).setCellStyle(style);
				}
				data_row.getCell(0).setCellValue(data.getModel());
				data_row.getCell(1).setCellValue(
						lang.equals("en") ? data.getBrand_en() : data
								.getBrand_cn());
				data_row.getCell(2).setCellValue(data.getActive_num());
				data_row.getCell(3).setCellStyle(ratio_style);
				data_row.getCell(3).setCellValue(Double.parseDouble(data.getActive_ratio()));
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_model_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportModelData error : " + e);
		}
	}

	/**
	 * 导出渠道数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportChannelData", method = RequestMethod.GET)
	public void exportChannelData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出渠道数据", "export channel data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String brand_id = request.getParameter("brand_id");
			String model = request.getParameter("model");
			String sort = request.getParameter("sort");
			String order = request.getParameter("order");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet(ExcelUtil.export_channel_sheetname);
			String[] header = ExcelUtil.export_channel_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_channel_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}

			// 数据行
			Integer total = analysisService.getChannelDataTotal(null, null,
					brand_id, model);
			Integer count = analysisService.getChannelDataCount(null, null,
					brand_id, model);

			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			List<StChannel> list = analysisService
					.getChannelDataList(null, 0, count, sort, order, null,
							brand_id, model, total, m_user_id);

			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle ratio_style = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ratio_style.setDataFormat(df.getFormat("#,##0.00"));
			ratio_style.setAlignment(CellStyle.ALIGN_LEFT);

			for (int i = 0; i < list.size(); i++) {
				StChannel data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
					data_row.getCell(j).setCellStyle(style);
				}
				data_row.getCell(0).setCellValue(data.getChannel());
				data_row.getCell(1).setCellValue(data.getActive_num());
				data_row.getCell(2).setCellStyle(ratio_style);
				data_row.getCell(2).setCellValue(Double.parseDouble(data.getActive_ratio()));
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_channel_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportChannelData error : " + e);
		}
	}

	/**
	 * 导出厂商分布数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportBrandData", method = RequestMethod.GET)
	public void exportBrandData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出厂商分布数据", "export brand ratio data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String sort = request.getParameter("sort");
			String order = request.getParameter("order");
			String brand_id = request.getParameter("brand_id");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet(ExcelUtil.export_brand_sheetname);
			String[] header = ExcelUtil.export_brand_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_brand_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}
			
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			// 数据行
			Integer total = analysisService.getBrandDataTotal(null, null,
					brand_id, start_date, end_date, m_user_id);
			Integer count = analysisService.getBrandDataCount(null, null,
					brand_id, start_date, end_date, m_user_id);
			List<StChannel> list = analysisService.getBrandDataList(0, count,
					sort, order, total, brand_id, start_date, end_date, m_user_id);

			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle ratio_style = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ratio_style.setDataFormat(df.getFormat("#,##0.00"));
			ratio_style.setAlignment(CellStyle.ALIGN_LEFT);

			for (int i = 0; i < list.size(); i++) {
				StChannel data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
					data_row.getCell(j).setCellStyle(style);
				}
				data_row.getCell(0).setCellValue(
						lang.equals("en") ? data.getBrand_en() : data
								.getBrand_cn());
				data_row.getCell(1).setCellValue(data.getActive_num());
				data_row.getCell(2).setCellStyle(ratio_style);
				data_row.getCell(2).setCellValue(Double.parseDouble(data.getActive_ratio()));
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_brand_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportBrandData error : " + e);
		}
	}

	/**
	 * 导出机型分布数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exportBrandModelData", method = RequestMethod.GET)
	public void exportBrandModelData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		session = request.getSession();

		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出机型分布数据", "export model ratio data", 6, 1));
			
			String lang = (String) session.getAttribute("lang");
			String brand_id = request.getParameter("brand_id");
			String sort = request.getParameter("sort");
			String order = request.getParameter("order");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");

			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb
					.createSheet(ExcelUtil.export_brand_model_sheetname);
			String[] header = ExcelUtil.export_brand_model_header_cn;
			if (StringUtils.isNotEmpty(lang) && lang.equals("en")) {
				header = ExcelUtil.export_brand_model_header_en;
			}

			// 这是第一行列名
			Row row = sheet.createRow(0);
			for (int i = 0; i < header.length; i++) {
				sheet.setColumnWidth(i, 51 * 100);
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}

			// 数据行
			Integer total = 0;
			Integer count = 0;
			List<StChannel> list = new ArrayList<>();

			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			total = analysisService.getBrandModelDataTotal(brand_id, user_id,
					start_date, end_date, m_user_id);
			count = analysisService.getBrandModelDataCount(brand_id, user_id,
					start_date, end_date, m_user_id);
			list = analysisService.getBrandModelDataList(0, count, sort, order,
					brand_id, total, user_id, start_date, end_date, m_user_id);

			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle ratio_style = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ratio_style.setDataFormat(df.getFormat("#,##0.00"));
			ratio_style.setAlignment(CellStyle.ALIGN_LEFT);

			for (int i = 0; i < list.size(); i++) {
				StChannel data = list.get(i);
				Row data_row = sheet.createRow(i + 1);
				for (int j = 0; j < header.length; j++) {
					data_row.createCell(j);
					data_row.getCell(j).setCellStyle(style);
				}
				data_row.getCell(0).setCellValue(data.getModel());
				data_row.getCell(1).setCellValue(data.getActive_num());
				data_row.getCell(2).setCellStyle(ratio_style);
				data_row.getCell(2).setCellValue(Double.parseDouble(data.getActive_ratio()));
			}

			// 输出Excel文件
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(
									(ExcelUtil.export_brand_model_filename + ".xlsx")
											.getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportBrandModelData error : " + e);
		}
	}

	/**
	 * 首页数据
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("dashboardData")
	@ResponseBody
	public String dashboardData(String brand_id) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			int stDayYes = 0;
			int stDay7 = 0;
			int stDay30 = 0;
			int stDayAll = 0;
			String dayYes = DateUtil.getDate(-1);
			String day7 = DateUtil.getDate(-7);
			String day30 = DateUtil.getDate(-30);
			List<StDay> list = new ArrayList<>();
			User user = getUser();
			String user_id = null;
			String m_user_id = null;
			if (user.getFuser() != 1) {// 子账号
				user_id = String.valueOf(user.getId());
			}
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}

			list = analysisService.getDashboardData(brand_id, user_id,
					m_user_id);

			for (StDay data : list) {
				stDayAll = stDayAll + data.getActive_num();
				if (dayYes.equals(data.getActive_date())) {
					stDayYes = data.getActive_num();
				}
				if (DateUtil.compareDate(data.getActive_date(), day7)) {
					stDay7 = stDay7 + data.getActive_num();
				}
				if (DateUtil.compareDate(data.getActive_date(), day30)) {
					stDay30 = stDay30 + data.getActive_num();
				}
			}
			jsonObject.put("stDayYes", stDayYes);
			jsonObject.put("stDay7", stDay7);
			jsonObject.put("stDay30", stDay30);
			jsonObject.put("stDayAll", stDayAll);
		} catch (Exception ex) {
			log.error("dashboardData error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 导出销量明细数据
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("exportStDetail")
	@ResponseBody
	public String exportStDetail(String brand_id, String model)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			String file_path = Constant.st_file_url + Constant.separate
					+ brand_id + Constant.separate + model + Constant.separate
					+ Constant.st_file_name;
			log.info("exportStDetail file_path = " + file_path);
			File file = new File(file_path);
			if (file.exists()) {// 文件存在
				jsonObject.put("status", 1);
				jsonObject.put("message", file_path);
			} else {
				jsonObject.put("status", 0);
			}
		} catch (Exception ex) {
			log.error("exportStDetail error : " + ex);
			jsonObject.put("status", 0);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 下载明细文件
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "downloadStFile", method = RequestMethod.GET)
	@ResponseBody
	public void downloadStFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"导出销量明细数据", "export sales detail data", 6, 1));
			
			String file_url = URLDecoder.decode(request.getParameter("file_url"),"UTF-8");
			String file_name = URLDecoder.decode(request.getParameter("file_name"),"UTF-8");
			
			File file = new File(file_url);
			response.reset();
			// 设置文件MIME类型
			response.setContentType(FileUtil.getMIMEType(file)
					+ ";charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + file_name + "\"");
					
			// 读取文件
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(file_url);
				// 读取目标文件，通过response将目标文件写到客户端
				out = response.getOutputStream();
				// 写文件
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}
		} catch (Exception ex) {
			log.error("downloadStFile error : " + ex);
		}
	}

}
