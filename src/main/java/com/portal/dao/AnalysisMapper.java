package com.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.model.StChannel;
import com.portal.model.StDay;
import com.portal.model.StDetail;

@Repository
public interface AnalysisMapper extends BaseMapper<StDay> {

	List<StDay> getActiveDataList(@Param("search") String search,
			@Param("offset") Integer offset, @Param("limit") Integer limit,
			@Param("sort") String sort, @Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getActiveDataCount(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	int getActiveDataTotal(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	List<StDay> getModelDataTable(@Param("search") String search,
			@Param("offset") Integer offset, @Param("limit") Integer limit,
			@Param("sort") String sort, @Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getModelDataTableCount(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	int getModelDataTableTotal(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	List<StDay> getModelDataTrend(@Param("params") Map<String, Object> params);
	
	List<StDay> getBrandDataTrend(@Param("params") Map<String, Object> params);

	List<StChannel> getChannelDataList(@Param("search") String search,
			@Param("offset") Integer offset, @Param("limit") Integer limit,
			@Param("sort") String sort, @Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getChannelDataCount(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	int getChannelDataTotal(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	List<StDetail> getDetailDataList(@Param("params") Map<String, Object> params);

	List<StChannel> getBrandDataList(@Param("offset") Integer offset,
			@Param("limit") Integer limit, @Param("sort") String sort,
			@Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getBrandDataCount(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	int getBrandDataTotal(@Param("search") String search,
			@Param("params") Map<String, Object> params);

	List<StChannel> getBrandModelDataList(@Param("offset") Integer offset,
			@Param("limit") Integer limit, @Param("sort") String sort,
			@Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getBrandModelDataCount(@Param("params") Map<String, Object> params);

	int getBrandModelDataTotal(@Param("params") Map<String, Object> params);
	
	List<StDay> getDashboardData(@Param("params") Map<String, Object> params);

}