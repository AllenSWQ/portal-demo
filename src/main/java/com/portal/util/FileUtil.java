package com.portal.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static String getAPKFileSize(MultipartFile file) {
		if (file != null) {
			//return String.valueOf(file.length());
			return String.valueOf(file.getSize());
		}
		return null;
	}
	
	/**
	 * 文件重命名
	 * 
	 * @param str
	 * @return
	 */
	public static String getNewStrName(String str) {
		String extName = ""; // 保存文件拓展名
		String newFileName = ""; // 保存新的文件名
		String nowTimeStr = ""; // 保存当前时间
		SimpleDateFormat sDateFormat;
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (999999 - 100000 + 1)) + 100000; // 获取随机数
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
		nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		// 获取拓展名
		if (str.lastIndexOf(".") >= 0) {
			extName = str.substring(str.lastIndexOf("."));
		}
		newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
		return newFileName;
	}

	/**
	 * 创建目录
	 * 
	 * @param path
	 * @return
	 */
	public static boolean mkdirPath(String path) {
		boolean result = true;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * 保存上传的文件
	 * 
	 * @param targetPath
	 *            目录路径
	 * @param targetFile
	 *            目录文件
	 * @param targetFileName
	 *            目录文件名
	 * @return
	 */
	public static String copyFile(String targetPath, MultipartFile targetFile,
			String targetFileName) {
		String storeFile = targetPath;
		String newFileName = getNewStrName(targetFileName); // 文件重命名后的名字

		if (targetFile != null) {
			File saveFile = new File(storeFile, newFileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			try {
				copy(targetFile, saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newFileName;
	}

	public static void copy(MultipartFile src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = src.getInputStream();
				//in = new BufferedInputStream(new FileInputStream(src), 30 * 1024);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						30 * 1024);
				byte[] buff = new byte[30 * 1024];
				int rc = 0;
				while ((rc = in.read(buff)) > 0) {
					out.write(buff, 0, rc);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * 
	 * @param file
	 */
	public static String getMIMEType(File file) {
		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) {
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	public static final String[][] MIME_MapTable = {
			// {后缀名， MIME类型}
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".pdf", "application/pdf" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".txt", "text/plain" }, { ".wps", "application/vnd.ms-works" },
			{ ".jpg", "image/jpeg" },
			{ ".gif", "image/gif" },
			{ ".png", "image/png" },
			{ ".csv", "text/comma-separated-values" },
			{ "", "*/*" } };
}
