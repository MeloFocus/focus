package com.melo.focus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.melo.focus.domain.basic.User;
import com.melo.focus.domain.vm.UserRoleSaveVMS;
import com.melo.focus.domain.vm.UserSaveVM;
import com.melo.focus.domain.vm.UserUpdateVM;
import com.melo.focus.service.UserRoleService;
import com.melo.focus.service.UserService;
import com.melo.focus.util.Constants;
import com.melo.focus.util.Message;

@Api(description="对用户操作")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;

	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="新增用户",notes="新增用户，需要 user_saveUser权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_saveUser","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user",method=RequestMethod.POST)
	public Message<String> saveUser(UserSaveVM user){
		userService.saveUser(user);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="修改用户",notes="修改用户，需要user_updateUser 权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_updateUser","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user",method=RequestMethod.PUT)
	public Message<String> updateUser(UserUpdateVM user){
		userService.updateUser(user);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="删除用户",notes="删除用户，需要 user_deleteUser权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_deleteUser","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user/{id}",method=RequestMethod.DELETE)
	public Message<String> deleteUser(@PathVariable("id")String id){
		userService.deleteUser(id);
		return Message.ok(Constants.SUCCESS);

	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="将角色授予用户",notes="将角色授予用户，需要 user_delegate权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_delegate","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user/delegate",method=RequestMethod.POST)
	public Message<String> delegate(UserRoleSaveVMS userRoleVMS){
		userRoleService.delegate(userRoleVMS);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="查所有用户",notes="查所有用户，需要 user_findAllUser权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_findAllUser","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user",method=RequestMethod.GET)
	public Message<List<User>> findAllUser(){
		List<User> userList=userService.findAllUser();
		return Message.ok(userList);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="校验用户名唯一性，账号",notes="校验用户名唯一性，需要 user_isExist权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_isExist","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user/isExist",method=RequestMethod.GET)
	public Message<Boolean> isExist(/*@RequestParam(value="loginName")*/String loginName){
		return Message.ok(userService.isExist(loginName));
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="查单个用户所拥有的角色id",notes="查单个用户所拥有的角色id，需要 user_getRoleByUser权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"user_getRoleByUser","administrator"},logical=Logical.OR)
	@RequestMapping(value="/focus/user/getRoleByUser",method=RequestMethod.GET)
	public Message<List<String>>getRoleByUser(@RequestParam(value="userId")String userId){
		return Message.ok(userRoleService.getRoleByUser(userId));
	}
}
