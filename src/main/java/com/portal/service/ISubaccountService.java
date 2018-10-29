package com.portal.service;

import java.util.List;

import com.portal.model.Permission;
import com.portal.model.RolePer;
import com.portal.model.SubModel;
import com.portal.model.SubPer;
import com.portal.model.User;

public interface ISubaccountService {

	public List<User> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand, String fuser)
			throws Exception;

	int getCount(String search, String filter, String brand, String fuser)
			throws Exception;
	
	List<String> getModelSelectBySub(Integer uid) throws Exception;
	
	List<Permission> getAllPermByUID(Integer uid) throws Exception;
	
	public List<RolePer> getPerSelectBySub(Integer uid) throws Exception;
	
	int deleteModelBySub(Integer uid) throws Exception;
	
	int deletePerBySub(Integer uid) throws Exception;
	
	int insertSubModel(List<SubModel> list) throws Exception;
	
	int insertSubPer(List<SubPer> list) throws Exception;
	
	public int addUser(User user) throws Exception;

}
