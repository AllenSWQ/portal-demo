package com.portal.util;

public class ExcelUtil {
	public final static String export_detail_filename = "device_data";
	public final static String export_detail_sheetname = "device_data";
	public final static String[] export_detail_header_cn = { "厂商", "机型", "IMEI", "IMEI2", "激活日期", "最近登录日期", "激活版本号", "当前版本号" };
	public final static String[] export_detail_header_en = { "Brand", "Model", "IMEI", "IMEI2", "Active Date", "Last Login Date", "Active Version", "Current Version" };
	
	public final static String export_active_filename = "active_data";
	public final static String export_active_sheetname = "active_data";
	public final static String[] export_active_header_cn = { "激活日期", "激活数", "激活占比（%）" };
	public final static String[] export_active_header_en = { "Activation Date", "Activation Number", "Occupation Ratio(%)" };
	
	public final static String export_model_filename = "model_data";
	public final static String export_model_sheetname = "model_data";
	public final static String[] export_model_header_cn = { "机型", "厂商", "激活数", "激活占比（%）" };
	public final static String[] export_model_header_en = { "Model", "Brand", "Activation Number", "Occupation Ratio(%)" };
	
	public final static String export_channel_filename = "channel_data";
	public final static String export_channel_sheetname = "channel_data";
	public final static String[] export_channel_header_cn = { "渠道号", "激活数", "激活占比（%）" };
	public final static String[] export_channel_header_en = { "Channel Code", "Activation Number", "Occupation Ratio(%)" };
	
	public final static String export_brand_filename = "brand_data";
	public final static String export_brand_sheetname = "brand_data";
	public final static String[] export_brand_header_cn = { "厂商", "激活数", "激活占比（%）" };
	public final static String[] export_brand_header_en = { "Brand", "Activation Number", "Occupation Ratio(%)" };
	
	public final static String export_brand_model_filename = "brand_model_data";
	public final static String export_brand_model_sheetname = "brand_model_data";
	public final static String[] export_brand_model_header_cn = { "机型", "激活数", "激活占比（%）" };
	public final static String[] export_brand_model_header_en = { "Model", "Activation Number", "Occupation Ratio(%)" };
}
