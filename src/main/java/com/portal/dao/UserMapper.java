package com.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.model.Permission;
import com.portal.model.Role;
import com.portal.model.User;
import com.portal.model.UserBrand;

@Repository
public interface UserMapper extends BaseMapper<User> {

	List<Role> getAllRole(@Param("search") String search,
			@Param("offset") Integer offset, @Param("limit") Integer limit,
			@Param("sort") String sort, @Param("order") String order,
			@Param("params") Map<String, Object> params);

	List<Permission> getAllPerm();

	List<User> getRoleUser(@Param("roleid") String roleid);

	User Login(User user);

	List<Role> isrole(@Param("userid") String uid);

	int getRoleCount(@Param("search")String search,@Param("params")Map<String, Object> params);
	
	int getUserByUserName(@Param("name") String name);
	
	int getUserByEmail(@Param("email") String email);
	
	User getUserByEmailForgetPass(@Param("email") String email);
	
	int resetPassByEmail(@Param("email") String email, @Param("pwd") String pwd);
	
	int updateSubaccountState(@Param("userid") Integer userid, @Param("state") Integer state);
	
	int deleteSubPerByFuserRid(@Param("userid") String userid, @Param("roleid") String roleid);
	
	int insertUserBrand(@Param("list") List<UserBrand> list);
	
	List<String> getUserBrandSelectByID(@Param("uid") Integer uid);
	
	int deleteUserBrandByID(@Param("uid") Integer uid);

}