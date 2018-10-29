package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portal.dao.SubaccountMapper;
import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.RolePer;
import com.portal.model.SubModel;
import com.portal.model.SubPer;
import com.portal.model.User;
import com.portal.service.ISubaccountService;
import com.alibaba.druid.util.StringUtils;

@Service("subaccountService")
public class SubaccountServiceImpl implements ISubaccountService {

	@Resource
	private SubaccountMapper subaccountMapper;

	public User getUserById(Integer id) {
		return (User) subaccountMapper.selectByPrimaryKey(id);
	}

	public List<User> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand, String fuser) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand)) {
			params.put("brand", brand);
		}
		if (!StringUtils.isEmpty(fuser)) {
			params.put("fuser", fuser);
		}
		return subaccountMapper.getAll(search, offset, limit, sort, order, params);
	}

	@Override
	public int getCount(String search, String filter, String brand, String fuser) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(brand)) {
			params.put("brand", brand);
		}
		if (!StringUtils.isEmpty(fuser)) {
			params.put("fuser", fuser);
		}
		return subaccountMapper.getCount(search, params);
	}
	
	public int logMsg(LogData log) {
		// TODO Auto-generated method stub
		return subaccountMapper.logMsg(log);
	}

	@Override
	public List<String> getModelSelectBySub(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.getModelSelectBySub(uid);
	}

	@Override
	public List<Permission> getAllPermByUID(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.getAllPermByUID(uid);
	}

	@Override
	public List<RolePer> getPerSelectBySub(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.getPerSelectBySub(uid);
	}

	@Override
	public int deleteModelBySub(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.deleteModelBySub(uid);
	}

	@Override
	public int deletePerBySub(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.deletePerBySub(uid);
	}

	@Override
	public int insertSubModel(List<SubModel> list) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.insertSubModel(list);
	}

	@Override
	public int insertSubPer(List<SubPer> list) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.insertSubPer(list);
	}

	@Override
	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return subaccountMapper.insertSelective(user);
	}

}
