package com.portal.service;

import java.util.List;

import com.portal.model.Model;

public interface IModelService {

	public List<Model> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String channel_id, String model, String mul_uid) throws Exception;

	int getCount(String search, String filter, String brand_id,
			String channel_id, String model, String mul_uid) throws Exception;
	
	public List<Model> getSelect(String brand_id, String channel_id, String sub_uid, String mul_uid) throws Exception;
	
	int updateModel(String id, String cycle) throws Exception;

}
