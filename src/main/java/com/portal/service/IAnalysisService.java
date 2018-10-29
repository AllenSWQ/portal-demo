package com.portal.service;

import java.util.List;

import com.portal.model.StChannel;
import com.portal.model.StDay;
import com.portal.model.StDetail;

public interface IAnalysisService {

	public List<StDay> getActiveDataList(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, String start_date, String end_date,
			Integer total, String sub_uid, String mul_uid) throws Exception;

	int getActiveDataCount(String search, String filter, String brand_id,
			String model, String start_date, String end_date, String sub_uid, String mul_uid) throws Exception;

	int getActiveDataTotal(String search, String filter, String brand_id,
			String model, String start_date, String end_date, String sub_uid, String mul_uid) throws Exception;

	public List<StDay> getModelDataTable(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, String start_date, String end_date,
			Integer total, String sub_uid, String mul_uid) throws Exception;

	int getModelDataTableCount(String search, String filter, String brand_id,
			String model, String start_date, String end_date, String sub_uid, String mul_uid) throws Exception;

	int getModelDataTableTotal(String search, String filter, String brand_id,
			String model, String start_date, String end_date, String sub_uid, String mul_uid) throws Exception;

	public List<StDay> getModelDataTrend(String brand_id, String model,
			String start_date, String end_date, String sub_uid, String mul_uid) throws Exception;
	
	public List<StDay> getBrandDataTrend(String brand_id, 
			String start_date, String end_date, String mul_uid) throws Exception;

	public List<StChannel> getChannelDataList(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, Integer total, String mul_uid) throws Exception;

	int getChannelDataCount(String search, String filter, String brand_id,
			String model) throws Exception;

	int getChannelDataTotal(String search, String filter, String brand_id,
			String model) throws Exception;

	public List<StDetail> getDetailDataList(String brand_id, String model)
			throws Exception;

	public List<StChannel> getBrandDataList(Integer offset, Integer limit,
			String sort, String order, Integer total, String brand_id, String start_date, String end_date, String mul_uid) throws Exception;

	int getBrandDataCount(String search, String filter,
			String brand_id, String start_date, String end_date, String mul_uid) throws Exception;

	int getBrandDataTotal(String search, String filter,
			String brand_id, String start_date, String end_date, String mul_uid) throws Exception;

	public List<StChannel> getBrandModelDataList(Integer offset, Integer limit,
			String sort, String order, String brand_id, Integer total, String sub_uid, String start_date, String end_date, String mul_uid)
			throws Exception;

	int getBrandModelDataCount(String brand_id, String sub_uid, String start_date, String end_date, String mul_uid) throws Exception;

	int getBrandModelDataTotal(String brand_id, String sub_uid, String start_date, String end_date, String mul_uid) throws Exception;
	
	public List<StDay> getDashboardData(String brand_id, String sub_uid, String mul_uid) throws Exception;

}
