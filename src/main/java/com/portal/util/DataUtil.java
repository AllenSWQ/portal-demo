package com.portal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portal.model.StChannel;
import com.portal.model.StDay;

public class DataUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 用户新增走势过滤（单条数据）- yyyy-MM-dd
	 * @param list
	 * @return
	 */
	public static List<StDay> filterData(List<StDay> list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			for (StDay obj : list) {
				if (compareDate(obj.getActive_date(), minDate)) {//若数据日期大于minDate
					do {
						repairObj = new StDay();
						repairObj.setActive_num(0);
						repairObj.setActive_date(minDate);
						filter_list.add(repairObj);
						minDate = datePlus(minDate, 1);//递增一天
					} while (!obj.getActive_date().equals(minDate));
				}
				filter_list.add(obj);//把该日数据加入list
				if (minDate.equals(maxDate)) {
					break;
				}
				minDate = datePlus(minDate, 1);//递增一天
			}
		}

		return filter_list;
	}
	
	/**
	 * 用户新增走势过滤（单条数据）- yyyy-MM
	 * @param list
	 * @return
	 */
	public static List<StDay> filterDataYM(List<StDay> list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			for (StDay obj : list) {
				if (compareDateYM(obj.getActive_date(), minDate)) {//若数据日期大于minDate
					do {
						repairObj = new StDay();
						repairObj.setActive_num(0);
						repairObj.setActive_date(minDate);
						filter_list.add(repairObj);
						minDate = datePlusYM(minDate, 1);//递增一月
					} while (!obj.getActive_date().equals(minDate));
				}
				filter_list.add(obj);//把该日数据加入list
				if (minDate.equals(maxDate)) {
					break;
				}
				minDate = datePlusYM(minDate, 1);//递增一月
			}
		}

		return filter_list;
	}
	
	/**
	 * 机型走势过滤（多条数据）- yyyy-MM-dd
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<StDay> filterMultipleData(List<StDay> list, List<StDay> model_list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			Map map = new HashMap<String, String>();
			for (StDay model : model_list){
				if (!map.containsKey(model.getModel())){
					map.put(model.getModel(), model.getModel());
					minDate = list.get(0).getActive_date();
					maxDate = list.get(list.size() - 1).getActive_date();
					for (StDay obj : list) {
						if (obj.getModel().equals(model.getModel())){
							if (compareDate(obj.getActive_date(), minDate)) {//若数据日期大于minDate
								do {
									repairObj = new StDay();
									repairObj.setActive_num(0);
									repairObj.setActive_date(minDate);
									repairObj.setModel(model.getModel());
									filter_list.add(repairObj);
									minDate = datePlus(minDate, 1);//递增一天
								} while (!obj.getActive_date().equals(minDate));
							}
							filter_list.add(obj);//把该日数据加入list
							if (minDate.equals(maxDate)) {
								break;
							}
							minDate = datePlus(minDate, 1);//递增一天
						}
					}
					if (!datePlus(minDate, -1).equals(maxDate)){
						do {
							repairObj = new StDay();
							repairObj.setActive_num(0);
							repairObj.setActive_date(minDate);
							repairObj.setModel(model.getModel());
							filter_list.add(repairObj);
							minDate = datePlus(minDate, 1);//递增一天
						} while (!maxDate.equals(datePlus(minDate, -1)));
					}
				}
			}
		}

		return filter_list;
	}
	
	/**
	 * 机型走势过滤（多条数据）- yyyy-MM
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<StDay> filterMultipleDataYM(List<StDay> list, List<StDay> model_list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			Map map = new HashMap<String, String>();
			for (StDay model : model_list){
				if (!map.containsKey(model.getModel())){
					map.put(model.getModel(), model.getModel());
					minDate = list.get(0).getActive_date();
					maxDate = list.get(list.size() - 1).getActive_date();
					for (StDay obj : list) {
						if (obj.getModel().equals(model.getModel())){
							if (compareDateYM(obj.getActive_date(), minDate)) {//若数据日期大于minDate
								do {
									repairObj = new StDay();
									repairObj.setActive_num(0);
									repairObj.setActive_date(minDate);
									repairObj.setModel(model.getModel());
									filter_list.add(repairObj);
									minDate = datePlusYM(minDate, 1);//递增一天
								} while (!obj.getActive_date().equals(minDate));
							}
							filter_list.add(obj);//把该日数据加入list
							if (minDate.equals(maxDate)) {
								break;
							}
							minDate = datePlusYM(minDate, 1);//递增一天
						}
					}
					if (!datePlusYM(minDate, -1).equals(maxDate)){
						do {
							repairObj = new StDay();
							repairObj.setActive_num(0);
							repairObj.setActive_date(minDate);
							repairObj.setModel(model.getModel());
							filter_list.add(repairObj);
							minDate = datePlusYM(minDate, 1);//递增一天
						} while (!maxDate.equals(datePlusYM(minDate, -1)));
					}
				}
			}
		}

		return filter_list;
	}
	
	/**
	 * 厂商走势过滤（多条数据）- yyyy-MM-dd
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<StDay> filterMultipleDataForBrand(List<StDay> list, List<StChannel> brand_list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			Map map = new HashMap<String, String>();
			for (StChannel brand : brand_list){
				if (!map.containsKey(brand.getBrand_id())){
					map.put(brand.getBrand_id(), brand.getBrand_id());
					minDate = list.get(0).getActive_date();
					maxDate = list.get(list.size() - 1).getActive_date();
					for (StDay obj : list) {
						if (obj.getBrand_id().equals(brand.getBrand_id())){
							if (compareDate(obj.getActive_date(), minDate)) {//若数据日期大于minDate
								do {
									repairObj = new StDay();
									repairObj.setActive_num(0);
									repairObj.setActive_date(minDate);
									repairObj.setBrand_id(brand.getBrand_id());
									repairObj.setBrand_cn(brand.getBrand_cn());
									repairObj.setBrand_en(brand.getBrand_en());
									filter_list.add(repairObj);
									minDate = datePlus(minDate, 1);//递增一天
								} while (!obj.getActive_date().equals(minDate));
							}
							filter_list.add(obj);//把该日数据加入list
							if (minDate.equals(maxDate)) {
								break;
							}
							minDate = datePlus(minDate, 1);//递增一天
						}
					}
					if (!datePlus(minDate, -1).equals(maxDate)){
						do {
							repairObj = new StDay();
							repairObj.setActive_num(0);
							repairObj.setActive_date(minDate);
							repairObj.setBrand_id(brand.getBrand_id());
							repairObj.setBrand_cn(brand.getBrand_cn());
							repairObj.setBrand_en(brand.getBrand_en());
							filter_list.add(repairObj);
							minDate = datePlus(minDate, 1);//递增一天
						} while (!maxDate.equals(datePlus(minDate, -1)));
					}
				}
			}
		}

		return filter_list;
	}
	
	/**
	 * 厂商走势过滤（多条数据）- yyyy-MM
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<StDay> filterMultipleDataForBrandYM(List<StDay> list, List<StChannel> brand_list) {
		List<StDay> filter_list = new ArrayList<>();
		if (list != null && list.size() != 0) {
			StDay repairObj = null;
			
			String minDate = list.get(0).getActive_date();
			String maxDate = list.get(list.size() - 1).getActive_date();
			Map map = new HashMap<String, String>();
			for (StChannel brand : brand_list){
				if (!map.containsKey(brand.getBrand_id())){
					map.put(brand.getBrand_id(), brand.getBrand_id());
					minDate = list.get(0).getActive_date();
					maxDate = list.get(list.size() - 1).getActive_date();
					for (StDay obj : list) {
						if (obj.getBrand_id().equals(brand.getBrand_id())){
							if (compareDateYM(obj.getActive_date(), minDate)) {//若数据日期大于minDate
								do {
									repairObj = new StDay();
									repairObj.setActive_num(0);
									repairObj.setActive_date(minDate);
									repairObj.setBrand_id(brand.getBrand_id());
									repairObj.setBrand_cn(brand.getBrand_cn());
									repairObj.setBrand_en(brand.getBrand_en());
									filter_list.add(repairObj);
									minDate = datePlusYM(minDate, 1);//递增一天
								} while (!obj.getActive_date().equals(minDate));
							}
							filter_list.add(obj);//把该日数据加入list
							if (minDate.equals(maxDate)) {
								break;
							}
							minDate = datePlusYM(minDate, 1);//递增一天
						}
					}
					if (!datePlusYM(minDate, -1).equals(maxDate)){
						do {
							repairObj = new StDay();
							repairObj.setActive_num(0);
							repairObj.setActive_date(minDate);
							repairObj.setBrand_id(brand.getBrand_id());
							repairObj.setBrand_cn(brand.getBrand_cn());
							repairObj.setBrand_en(brand.getBrand_en());
							filter_list.add(repairObj);
							minDate = datePlusYM(minDate, 1);//递增一天
						} while (!maxDate.equals(datePlusYM(minDate, -1)));
					}
				}
			}
		}

		return filter_list;
	}

	/**
	 * 日期加n天
	 * 
	 * @param dateStr
	 * @param i
	 * @return
	 */
	public static String datePlus(String dateStr, int n) {
		try {
			Date date = new Date();
			date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, n);
			dateStr = sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	/**
	 * 日期加n月 
	 * 
	 * @param dateStr
	 * @param i
	 * @return
	 */
	public static String datePlusYM(String dateStr, int n) {
		try {
			Date date = new Date();
			date = ym.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, n);
			dateStr = ym.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 比较日期大小
	 * 
	 * @param key_time
	 * @param data_time
	 * @return
	 */
	public static boolean compareDate(String key_time, String data_time) {
		return StringDateToLong(key_time) > StringDateToLong(data_time);
	}
	
	/**
	 * 比较日期大小 - yyyy-MM
	 * 
	 * @param key_time
	 * @param data_time
	 * @return
	 */
	public static boolean compareDateYM(String key_time, String data_time) {
		return StringDateToLongYM(key_time) > StringDateToLongYM(data_time);
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
			Date date = sdf.parse(dateStr);
			t1 = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t1;
	}
	
	/**
	 * String转Long - yyyy-MM
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long StringDateToLongYM(String dateStr) {
		long t1 = new Date().getTime();
		try {
			Date date = ym.parse(dateStr);
			t1 = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t1;
	}

	public static void main(String[] args) {
	}
}
