package com.portal.service;

import java.util.List;

import com.portal.model.LogData;
import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserBrand;

public interface IUserService {

	public User getUserById(Integer id) throws Exception;

	public List<User> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String brand, String fuser) throws Exception;

	int getCount(String search, String filter, String brand, String fuser) throws Exception;

	int getRoleCount(String search, String filter) throws Exception;

	public int addUser(User user) throws Exception;

	public int delete(List<String> ids) throws Exception;

	public int delurole(String userid) throws Exception;

	public int addurole(String userid, String username) throws Exception;

	int updateByPrimaryKeySelective(User user) throws Exception;

	public User Login(User user) throws Exception;

	List<Role> getAllRole(String search, Integer offset, Integer limit,
			String sort, String order, String filter) throws Exception;

	List<User> getRoleUser(String roleid) throws Exception;

	List<Permission> getAllPerm() throws Exception;

	List<Role> isrole(String uid) throws Exception;

	List<Permission> getUserMenu(String userid) throws Exception;
	
	List<Permission> getSubUserMenu(String userid) throws Exception;
	
	public int logMsg(LogData log);
	
	int getUserByUserName(String name) throws Exception;
	
	int getUserByEmail(String email) throws Exception;
	
	User getUserByEmailForgetPass(String email) throws Exception;
	
	int resetPassByEmail(String email, String pwd) throws Exception;
	
	int updateSubaccountState(Integer userid, Integer state) throws Exception;
	
	int deleteSubPerByFuserRid(String userid, String roleid) throws Exception;
	
	int insertUserBrand(List<UserBrand> list) throws Exception;
	
	List<String> getUserBrandSelectByID(Integer uid) throws Exception;
	
	int deleteUserBrandByID(Integer uid) throws Exception;

}
