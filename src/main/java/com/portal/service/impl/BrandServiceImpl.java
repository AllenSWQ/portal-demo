package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portal.dao.BrandMapper;
import com.portal.model.Brand;
import com.portal.service.IBrandService;
import com.alibaba.druid.util.StringUtils;

@Service("Brandservice")
public class BrandServiceImpl implements IBrandService {

	@Resource
	BrandMapper brandMapper;

	@Override
	public List<Brand> getSelect(String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return brandMapper.getSelect(params);
	}

	@Override
	public List<Brand> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return brandMapper.getAll(search, offset, limit, sort, order, params);
	}

	@Override
	public int getCount(String search, String filter, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return brandMapper.getCount(search, params);
	}

	@Override
	public int addBrand(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.insertSelective(brand);
	}

	@Override
	public int getBrandByCode(String brand_en, String brand_cn) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.getBrandByCode(brand_en, brand_cn);
	}

	@Override
	public int editBrand(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.updateByPrimaryKeySelective(brand);
	}

	@Override
	public int deleteBrand(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.deleteByID(id);
	}

	@Override
	public int updateUserState(Integer brand_id) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.updateUserState(brand_id);
	}

	@Override
	public int deluroleByBrandId(Integer brand_id) throws Exception {
		// TODO Auto-generated method stub
		return brandMapper.deluroleByBrandId(brand_id);
	}

}
