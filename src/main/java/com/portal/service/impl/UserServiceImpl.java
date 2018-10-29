package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.portal.dao.UserMapper;
import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserBrand;
import com.portal.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	public User getUserById(Integer id) {
		return (User) userMapper.selectByPrimaryKey(id);
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
		return userMapper.getAll(search, offset, limit, sort, order, params);
	}

	public User Login(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.Login(user);
	}

	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(user);
	}

	public int delete(List<String> ids) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.delete(ids);
	}

	public int delurole(String userid) throws Exception {
		return userMapper.delurole(userid);

	}

	public int addurole(String userid, String username) throws Exception {
		return userMapper.addurole(userid, username);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
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
		return userMapper.getCount(search, params);
	}

	@Override
	public int getRoleCount(String search, String filter) throws Exception {
		JSONObject obj = JSON.parseObject(filter);
		Map<String, Object> params = new HashMap<String, Object>();
		if (obj != null) {
			params.put("searchId", obj.get("id") != null ? obj.get("id")
					.toString() : "");
			params.put("searchName", obj.get("name") != null ? obj.get("name")
					.toString() : "");
		}
		return userMapper.getRoleCount(search, params);
	}

	@Override
	public List<Role> getAllRole(String search, Integer offset, Integer limit,
			String sort, String order, String filter) throws Exception {
		JSONObject obj = JSON.parseObject(filter);
		Map<String, Object> params = new HashMap<String, Object>();
		if (obj != null) {
			params.put("searchId", obj.get("id") != null ? obj.get("id")
					.toString() : "");
			params.put("searchName", obj.get("name") != null ? obj.get("name")
					.toString() : "");
		}
		return userMapper
				.getAllRole(search, offset, limit, sort, order, params);
	}

	@Override
	public List<User> getRoleUser(String roleid) throws Exception {
		return userMapper.getRoleUser(roleid);
	}

	@Override
	public List<Permission> getAllPerm() throws Exception {
		return userMapper.getAllPerm();
	}

	@Override
	public List<Role> isrole(String uid) throws Exception {
		return userMapper.isrole(uid);
	}

	@Override
	public List<Permission> getUserMenu(String userid) throws Exception {
		return userMapper.getUserMenu(userid);
	}
	
	public int logMsg(LogData log) {
		// TODO Auto-generated method stub
		return userMapper.logMsg(log);
	}
	
	@Override
	public int getUserByUserName(String name) throws Exception {
		return userMapper.getUserByUserName(name);
	}
	
	@Override
	public int getUserByEmail(String email) throws Exception {
		return userMapper.getUserByEmail(email);
	}

	@Override
	public int resetPassByEmail(String email, String pwd) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.resetPassByEmail(email, pwd);
	}

	@Override
	public User getUserByEmailForgetPass(String email) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserByEmailForgetPass(email);
	}

	@Override
	public int updateSubaccountState(Integer userid, Integer state)
			throws Exception {
		// TODO Auto-generated method stub
		return userMapper.updateSubaccountState(userid, state);
	}

	@Override
	public List<Permission> getSubUserMenu(String userid) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getSubUserMenu(userid);
	}

	@Override
	public int deleteSubPerByFuserRid(String userid, String roleid)
			throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteSubPerByFuserRid(userid, roleid);
	}

	@Override
	public int insertUserBrand(List<UserBrand> list) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.insertUserBrand(list);
	}

	@Override
	public List<String> getUserBrandSelectByID(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserBrandSelectByID(uid);
	}

	@Override
	public int deleteUserBrandByID(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteUserBrandByID(uid);
	}

}
