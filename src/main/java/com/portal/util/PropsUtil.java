package com.portal.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropsUtil {

	private static Log log = LogFactory.getLog(PropsUtil.class);

	private static Properties properties;

	private PropsUtil() {
	}

	static {
		properties = new Properties();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(
					"/data01/apps/config/portal-demo/config.properties"));
			properties.load(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("FileNotFoundException:" + e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}

	public static void main(String[] args) {
	}

}
