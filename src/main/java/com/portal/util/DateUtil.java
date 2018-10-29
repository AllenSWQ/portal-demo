package com.portal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil extends DateUtils {
	private static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取前一天时间
	 * @return
	 */
	public static String getDate(int num){
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
	    calendar.add(Calendar.DAY_OF_MONTH, num);   
		String time = sd.format(calendar.getTime());	
		return time;		
	}
	
	/**
	 * 比较日期大小
	 * 
	 * @param key_time
	 * @param data_time
	 * @return
	 */
	public static boolean compareDate(String key_time, String data_time) {
		return StringDateToLong(key_time) >= StringDateToLong(data_time);
	}

	/**
	 * String转Long
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long StringDateToLong(String dateStr) {
		long t1 = new Date().getTime();
		try {
			Date date = sd.parse(dateStr);
			t1 = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t1;
	}
	
	public static void main(String[] args) {
		System.out.println(getDate(-7));
	}
	
}
