package com.portal.service;

import java.util.List;

import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.RolePer;

public interface IPerService {

	public List<Role> getRoleList() throws Exception;

	public List<RolePer> getrolePers(String roleperid) throws Exception;

	public int delrolePer(String rid, String pid) throws Exception;

	public int addrolePer(String rid, String pid) throws Exception;

	int getCount(String search, String filter) throws Exception;

	public int add(Role role) throws Exception;

	public int delete(List<String> ids) throws Exception;

	int updateByPrimaryKeySelective(Role role) throws Exception;

	public int addPer(Permission per) throws Exception;

	public int editPer(Permission per) throws Exception;

	public int delPer(String id) throws Exception;

	public int delPerRole(String id) throws Exception;

	public int getperstate(String fid) throws Exception;

	public int setPerState(Integer state, Integer val) throws Exception;
}
