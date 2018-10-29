package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portal.dao.AnalysisMapper;
import com.portal.model.StChannel;
import com.portal.model.StDay;
import com.portal.model.StDetail;
import com.portal.service.IAnalysisService;
import com.alibaba.druid.util.StringUtils;

@Service("analysisService")
public class AnalysisServiceImpl implements IAnalysisService {

	@Resource
	private AnalysisMapper analysisMapper;

	@Override
	public List<StDay> getActiveDataList(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, String start_date, String end_date,
			Integer total, String sub_uid, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		params.put("total", total);
		return analysisMapper.getActiveDataList(search, offset, limit, sort,
				order, params);
	}

	@Override
	public int getActiveDataCount(String search, String filter,
			String brand_id, String model, String start_date, String end_date, String sub_uid, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getActiveDataCount(search, params);
	}

	@Override
	public int getActiveDataTotal(String search, String filter,
			String brand_id, String model, String start_date, String end_date, String sub_uid, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getActiveDataTotal(search, params);
	}

	@Override
	public List<StDay> getModelDataTable(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, String start_date, String end_date,
			Integer total, String sub_uid, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		params.put("total", total);
		return analysisMapper.getModelDataTable(search, offset, limit, sort,
				order, params);
	}

	@Override
	public int getModelDataTableCount(String search, String filter,
			String brand_id, String model, String start_date, String end_date, String sub_uid, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getModelDataTableCount(search, params);
	}

	@Override
	public int getModelDataTableTotal(String search, String filter,
			String brand_id, String model, String start_date, String end_date, String sub_uid, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getModelDataTableTotal(search, params);
	}

	@Override
	public List<StDay> getModelDataTrend(String brand_id, String model,
			String start_date, String end_date, String sub_uid, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getModelDataTrend(params);
	}
	
	@Override
	public List<StDay> getBrandDataTrend(String brand_id,
			String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getBrandDataTrend(params);
	}


	@Override
	public List<StChannel> getChannelDataList(String search, Integer offset,
			Integer limit, String sort, String order, String filter,
			String brand_id, String model, Integer total, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		params.put("total", total);
		return analysisMapper.getChannelDataList(search, offset, limit, sort,
				order, params);
	}

	@Override
	public int getChannelDataCount(String search, String filter,
			String brand_id, String model) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		return analysisMapper.getChannelDataCount(search, params);
	}

	@Override
	public int getChannelDataTotal(String search, String filter,
			String brand_id, String model) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		return analysisMapper.getChannelDataTotal(search, params);
	}

	@Override
	public List<StDetail> getDetailDataList(String brand_id, String model)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(model)) {
			params.put("model", model);
		}
		return analysisMapper.getDetailDataList(params);
	}

	@Override
	public List<StChannel> getBrandDataList(Integer offset, Integer limit,
			String sort, String order, Integer total, String brand_id,
			String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		params.put("total", total);
		return analysisMapper.getBrandDataList(offset, limit, sort, order,
				params);
	}

	@Override
	public int getBrandDataCount(String search, String filter,
			String brand_id, String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getBrandDataCount(search, params);
	}

	@Override
	public int getBrandDataTotal(String search, String filter,
			String brand_id, String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getBrandDataTotal(search, params);
	}

	@Override
	public List<StChannel> getBrandModelDataList(Integer offset, Integer limit,
			String sort, String order, String brand_id, Integer total, String sub_uid, String start_date, String end_date, String mul_uid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		params.put("total", total);
		return analysisMapper.getBrandModelDataList(offset, limit, sort, order,
				params);
	}

	@Override
	public int getBrandModelDataCount(String brand_id, String sub_uid, String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getBrandModelDataCount(params);
	}

	@Override
	public int getBrandModelDataTotal(String brand_id, String sub_uid, String start_date, String end_date, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(start_date)) {
			params.put("start_date", start_date);
		}
		if (!StringUtils.isEmpty(end_date)) {
			params.put("end_date", end_date);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getBrandModelDataTotal(params);
	}

	@Override
	public List<StDay> getDashboardData(String brand_id, String sub_uid, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand_id)) {
			params.put("brand_id", brand_id);
		}
		if (!StringUtils.isEmpty(sub_uid)) {
			params.put("sub_uid", sub_uid);
		}
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return analysisMapper.getDashboardData(params);
	}

}
