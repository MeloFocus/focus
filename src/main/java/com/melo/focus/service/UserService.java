package com.melo.focus.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.focus.domain.basic.User;
import com.melo.focus.domain.vm.UserRoleSaveVM;
import com.melo.focus.domain.vm.UserRoleSaveVMS;
import com.melo.focus.domain.vm.UserSaveVM;
import com.melo.focus.domain.vm.UserUpdateVM;
import com.melo.focus.mapper.basic.UserMapper;
import com.melo.focus.mapper.extend.UserExtendMapper;
import com.melo.focus.util.Asserts;
import com.melo.focus.util.DataValidateFiledException;


@Service
@Transactional
public class UserService {

	@Autowired
	UserExtendMapper userExtendMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRoleService userRoleService;
	
	/**
	 * 根据用户名查用户对象
	 * @param account
	 * @return
	 */
	public User selectByAccount(String account){
		User user = userExtendMapper.selectByName(account);
		return user;
	}

	public void saveUser(UserSaveVM user) {
		Asserts.validate(user,"user");
		
		User u=new User();
		u.setId(UUID.randomUUID().toString());
		u.setLoginName(user.getLoginName());
		u.setRealName(user.getRealName());
		u.setPassword(user.getPassword());
		u.setSuperman(false);
		userMapper.insert(u);
		
		//给用户一个普通的角色
		UserRoleSaveVMS userRole=new UserRoleSaveVMS();
		userRole.setRoleIds("a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01");//这应该按照普通用户角色 的code查id，我就不想写了
		userRole.setUserId(u.getId());
		userRoleService.delegate(userRole);
	}

	public void updateUser(UserUpdateVM user) {
		Asserts.validate(user,"user");
		
		//校验用户id是否存在
		
		User u=new User();
		u.setId(user.getId());
		u.setLoginName(user.getLoginName());
		u.setRealName(user.getRealName());
		u.setPassword(user.getPassword());
		
		userMapper.updateByPrimaryKey(u);
	}

	public void deleteUser(String id) {
		if(StringUtils.isBlank(id)){
			throw new DataValidateFiledException("删除的用户id不能为空");
		}
		
		//校验用户id是否存在
		
		userRoleService.deleteByUserId(id);//删除用户下的角色关系
		userMapper.deleteByPrimaryKey(id);
	}

	public List<User> findAllUser() {
		List<User> selectAll = userMapper.selectAll();
		return selectAll;
	}

	public Boolean isExist(String loginName) {
		return userExtendMapper.isExist(loginName)>0;
	}

	
}
