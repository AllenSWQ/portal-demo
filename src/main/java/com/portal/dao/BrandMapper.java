package com.portal.dao;

import org.apache.ibatis.annotations.Param;

import com.portal.model.Brand;

public interface BrandMapper extends BaseMapper<Brand> {
	
	int getBrandByCode(@Param("brand_en") String brand_en, @Param("brand_cn") String brand_cn);
	
	int updateUserState(@Param("brand_id") Integer brand_id);
	
	int deluroleByBrandId(@Param("brand_id") Integer brand_id);

}