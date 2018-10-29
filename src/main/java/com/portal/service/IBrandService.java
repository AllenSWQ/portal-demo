package com.portal.service;

import java.util.List;

import com.portal.model.Brand;


public interface IBrandService {

	public List<Brand> getSelect(String mul_uid) throws Exception;
	
	public List<Brand> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String mul_uid) throws Exception;

	int getCount(String search, String filter, String mul_uid) throws Exception;
	
	public int addBrand(Brand brand) throws Exception;
	
	int getBrandByCode(String brand_en, String brand_cn) throws Exception;
	
	int editBrand(Brand brand) throws Exception;
	
	int deleteBrand(Integer id) throws Exception;
	
	int updateUserState(Integer brand_id) throws Exception;
	
	int deluroleByBrandId(Integer brand_id) throws Exception;
	
}
