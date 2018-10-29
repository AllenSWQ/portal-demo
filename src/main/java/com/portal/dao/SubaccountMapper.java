package com.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.model.Permission;
import com.portal.model.RolePer;
import com.portal.model.SubModel;
import com.portal.model.SubPer;
import com.portal.model.User;

@Repository
public interface SubaccountMapper extends BaseMapper<User> {
	
	List<String> getModelSelectBySub(@Param("uid") Integer uid);
	
	List<Permission> getAllPermByUID(@Param("uid") Integer uid);
	
	List<RolePer> getPerSelectBySub(@Param("uid") Integer uid);
	
	int deleteModelBySub(@Param("uid") Integer uid);
	
	int deletePerBySub(@Param("uid") Integer uid);
	
	int insertSubModel(@Param("list") List<SubModel> list);
	
	int insertSubPer(@Param("list") List<SubPer> list);

}