package com.portal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.springframework.web.multipart.MultipartFile;

import com.portal.constant.Constant;
import com.portal.model.Channel;
import com.portal.model.ChannelDetail;
import com.portal.model.ChannelFile;
import com.portal.model.LogData;
import com.portal.model.User;
import com.portal.service.IChannelService;
import com.portal.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/channelController")
public class ChannelController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelController.class);

	@Resource
	private IChannelService channelService;

	HttpSession session;

	@RequestMapping("channel")
	public String channel() {
		return "channel/channel";
	}
	
	@RequestMapping("channelDetail")
	public String channelDetail(String channel_id, org.springframework.ui.Model model) {
		try {
			model.addAttribute("channel_id", channel_id);
		} catch (Exception ex) {
			log.error("channelDetail error : " + ex);
		}
		return "channel/channelDetail";
	}

	/**
	 * 渠道列表
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
	@RequestMapping("channelList")
	@ResponseBody
	public String channelList(String search, Integer offset, Integer limit,
			String sort, String order, String filter) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			// 本地需转码
			/*if (StringUtils.isNotEmpty(search)) {
				search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
			}*/
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			List<Channel> list = channelService.getAll(search, offset, limit,
					sort, order, filter, m_user_id);
			Integer count = channelService.getCount(search, filter, m_user_id);
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"查询渠道号管理", "search Channel Mngt", 1, 1));
		} catch (Exception ex) {
			log.error("channelList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 新增渠道号前缀
	 * 
	 * @param channel
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addChannel", method = RequestMethod.POST)
	@ResponseBody
	public String addChannel(@ModelAttribute("channel") Channel channel) throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			if (channelService.getChannelByChannel(channel.getChannel()) != 0) {// 验证唯一性
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else {
				int res = channelService.addChannel(channel);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "新增渠道号前缀: " + channel.toString(), "add channel prefix : "
								+ channel.toString(), 2, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("addChannel error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 删除渠道号
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "delChannel", method = RequestMethod.POST)
	@ResponseBody
	public String delChannel(@RequestParam(value = "id") Integer id)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = channelService.deleteChannel(id);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除渠道号前缀: id=" + id, "delete channel prefix : id=" + id, 4,
					res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("delChannel error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 渠道详情列表
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
	@RequestMapping("channelDetailList")
	@ResponseBody
	public String channelDetailList(String search, Integer offset, Integer limit,
			String sort, String order, String filter, Integer channel_id) throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<ChannelDetail> list = channelService.getDetailByChannelID(search, offset, limit,
					sort, order, filter, channel_id);
			Integer count = channelService.getDetailCount(search, filter, channel_id);
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
		} catch (Exception ex) {
			log.error("channelDetailList error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 更改渠道号状态
	 * 
	 * @param channelDetail
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updateChannelStatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateChannelStatus(
			@ModelAttribute("ChannelDetail") ChannelDetail channelDetail)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = channelService.updateChannelDetailState(channelDetail);
			logMsg(new LogData(
					getUser().getId(),
					getUser().getName(),
					getIp(),
					"修改渠道号状态: id = " + channelDetail.getId()
							+ ", is_product = " + channelDetail.getIs_product(),
					"update channel state: id = " + channelDetail.getId()
							+ ", is_product = " + channelDetail.getIs_product(),
					3, res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("updateChannelStatus error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 新增渠道号
	 * 
	 * @param brand
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "addChannelDetail", method = RequestMethod.POST)
	@ResponseBody
	public String addChannelDetail(
			@ModelAttribute("channelDetail") ChannelDetail channelDetail)
			throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			if (channelService.getChannelDetailByChannel(channelDetail
					.getChannel()) != 0) {// 验证唯一性
				jsonObject.put("message", "notunique");
				jsonObject.put("status", "notunique");
			} else {
				int res = channelService.addChannelDetail(channelDetail);
				logMsg(new LogData(getUser().getId(), getUser().getName(),
						getIp(), "新增渠道号: " + channelDetail.toString(),
						"add channel : " + channelDetail.toString(), 2, res));
				// 设置返回结果
				setMessage(res, jsonObject);
			}
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("addChannelDetail error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 修改渠道号
	 * 
	 * @param channelDetail
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editChannelDetail", method = RequestMethod.POST)
	@ResponseBody
	public String editChannelDetail(
			@ModelAttribute("ChannelDetail") ChannelDetail channelDetail)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = channelService.updateChannelDetail(channelDetail);
			logMsg(new LogData(
					getUser().getId(),
					getUser().getName(),
					getIp(),
					"修改渠道号: channelDetail = " + channelDetail.toString(),
					"update channel: channelDetail = " + channelDetail.toString(),
					3, res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("editChannelDetail error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 渠道下拉框
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("channelSelect")
	@ResponseBody
	public String channelSelect(
			@RequestParam(value = "brand_id") String brand_id)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			User user = getUser();
			String m_user_id = null;
			if (user.getType() == 2) {// 多厂商
				m_user_id = String.valueOf(user.getId());
			}
			
			List<Channel> channel = channelService.getSelect(brand_id, m_user_id);
			jsonObject.put("rows", channel);
		} catch (Exception ex) {
			log.error("channelSelect error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 加载渠道号文件列表
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("loadChannelFile")
	@ResponseBody
	public String loadChannelFile(
			@RequestParam(value = "channel_id") Integer channel_id)
			throws IOException {
		JSONObject jsonObject = new JSONObject(true);
		try {
			List<ChannelFile> file = channelService.getFileByID(channel_id);
			jsonObject.put("rows", file);
		} catch (Exception ex) {
			log.error("loadChannelFile error : " + ex);
		}
		return JSON.toJSONString(jsonObject);
	}
	
	/**
	 * 上传文件
	 * 
	 * @param upload_file
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadfile(@ModelAttribute("channel_file") ChannelFile channel_file,
			@RequestParam("upload_file") MultipartFile upload_file)
			throws IOException {
		JSONObject jsonObject = new JSONObject();

		try {
			
			handleFile(channel_file, upload_file);
			
			int res = channelService.addChannelFile(channel_file);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"上传附件: " + channel_file.toString(), "upload file : "
							+ channel_file.toString(), 2, res));
			// 设置返回结果
			setMessage(res, jsonObject);
			
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("uploadfile error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 文件处理
	 */
	public void handleFile(ChannelFile channel_file, MultipartFile upload_file)
			throws Exception {
		String file_path = Constant.file_url + Constant.separate + channel_file.getChannel_id()
				+ Constant.separate;

		if (FileUtil.mkdirPath(file_path)) {
			String newName = FileUtil.copyFile(file_path, upload_file,
					upload_file.getOriginalFilename());
			channel_file.setFile_url(file_path + newName);
			channel_file.setFile_size(String.valueOf(upload_file.getSize()));
			channel_file.setOld_file_name(upload_file.getOriginalFilename());
			channel_file.setNew_file_name(newName);
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFile(@ModelAttribute("id") Integer id)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		try {
			int res = channelService.deleteChannelFile(id);
			logMsg(new LogData(getUser().getId(), getUser().getName(), getIp(),
					"删除附件: id = " + id, "delete file: id = " + id, 4, res));
			// 设置返回结果
			setMessage(res, jsonObject);
		} catch (Exception ex) {
			jsonObject.put("message", ex.getMessage());
			jsonObject.put("status", "error");
			log.error("deleteFile error : " + ex);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String file_url = URLDecoder.decode(request.getParameter("file_url"),"UTF-8");
			String file_name = URLDecoder.decode(request.getParameter("file_name"),"UTF-8");
			
			File file = new File(file_url);
			response.reset();
			// 设置文件MIME类型
			response.setContentType(FileUtil.getMIMEType(file)
					+ ";charset=utf-8");
			// 设置Content-Disposition
			if (request.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				file_name = new String(file_name.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase()
					.indexOf("MSIE") > 0) {
				file_name = URLEncoder.encode(file_name, "UTF-8");// IE浏览器
			} else if (request.getHeader("User-Agent").toUpperCase()
					.indexOf("CHROME") > 0) {
				file_name = new String(file_name.getBytes("UTF-8"), "ISO8859-1");// 谷歌
			}
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
			log.error("downloadFile error : " + ex);
		}
	}

}
