package com.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.model.Model;

@Repository
public interface ModelMapper extends BaseMapper<Model> {

	List<Model> getSelect(@Param("brand_id") String brand_id,
			@Param("channel_id") String channel_id, @Param("sub_uid") String sub_uid, @Param("mul_uid") String mul_uid);

	int updateModel(@Param("id") String id, @Param("cycle") String cycle);

}