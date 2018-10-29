package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.portal.dao.RoleMapper;
import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.RolePer;
import com.portal.service.IPerService;

@Service("Perservice")
public class PerServiceImpl implements IPerService {

	@Resource
	RoleMapper roleMapper;

	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

	@Override
	public List<RolePer> getrolePers(String roleperid){
		return roleMapper.getrolePers(roleperid);
	}
	
	@Override
	public int addrolePer(String rid, String pid){
		return roleMapper.addrolePer(rid, pid);
	}
	
	@Override
	public int delrolePer(String rid, String pid){
		return roleMapper.delrolePer(rid, pid);
	}
	@Override
	public int getCount(String search, String filter) {
		JSONObject obj = JSON.parseObject(filter);	
		Map<String,Object> params = new HashMap<String, Object>();
		if(obj != null){
			params.put("searchId", obj.get("id") != null ? obj.get("id").toString() : "" );
			params.put("searchName", obj.get("name") != null ? obj.get("name").toString() : "");
			params.put("searchNcikName", obj.get("nickname") != null ? obj.get("nickname").toString() : "");
			params.put("searchCreatetime", obj.get("createtime") != null ? obj.get("createtime").toString() : "");
		}
		return roleMapper.getCount(search, params);
	}

	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(role);
	}
	
	public int addPer(Permission per) {
		// TODO Auto-generated method stub
		return roleMapper.addPer(per);
	}

	public int editPer(Permission per) {
		// TODO Auto-generated method stub
		return roleMapper.editPer(per);
	}
	
	public int delPer(String id){
		return roleMapper.delPer(id);
	}
	
	public int delPerRole(String id){
		return roleMapper.delPerRole(id);
	}
	
	public int setPerState(Integer state, Integer val){
		return roleMapper.setPerState(state, val);
	}
	
	public int getperstate(String fid){
		return roleMapper.getperstate(fid);
	}
	
	public int delete(List<String> ids) {
		// TODO Auto-generated method stub
		return roleMapper.delete(ids);
	}

	@Override
	public int updateByPrimaryKeySelective(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	
}
