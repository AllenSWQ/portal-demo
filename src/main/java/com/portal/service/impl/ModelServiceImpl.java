package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portal.dao.ModelMapper;
import com.portal.model.Model;
import com.portal.service.IModelService;
import com.alibaba.druid.util.StringUtils;

@Service("modelService")
public class ModelServiceImpl implements IModelService {

	@Resource
	private ModelMapper modelMapper;

	@Override
	public List<Model> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand_id,
			String channel_id, String model, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(channel_id)) {
			params.put("channel_id", channel_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return modelMapper.getAll(search, offset, limit, sort, order, params);
	}

	@Override
	public int getCount(String search, String filter, String brand_id,
			String channel_id, String model, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(channel_id)) {
			params.put("channel_id", channel_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return modelMapper.getCount(search, params);
	}

	@Override
	public List<Model> getSelect(String brand_id, String channel_id, String sub_uid, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		return modelMapper.getSelect(brand_id, channel_id, sub_uid, mul_uid);
	}

	@Override
	public int updateModel(String id, String cycle) throws Exception {
		// TODO Auto-generated method stub
		return modelMapper.updateModel(id, cycle);
	}

}
