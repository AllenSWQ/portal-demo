package com.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.portal.model.Role;

public interface RoleMapper extends BaseMapper<Role> {

	List<Role> getRoleList();
	
	int delPerRoleByRoleId(@Param("id")String id);
	
	int delUserRoleByRoleId(@Param("id")String id);
	
	int getRoleByRoleName(@Param("name") String name);
	
	int deleteSubPerByRidPid(@Param("rid") String rid, @Param("pid") String pid);
	
	int deleteSubPerByRid(@Param("rid") String rid);

}