package com.melo.focus.controller;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.melo.focus.domain.basic.Role;
import com.melo.focus.domain.vm.RoleResourceVM;
import com.melo.focus.domain.vm.RoleSaveVM;
import com.melo.focus.domain.vm.RoleUpdateVM;
import com.melo.focus.service.RoleResourceService;
import com.melo.focus.service.RoleService;
import com.melo.focus.util.Constants;
import com.melo.focus.util.Message;


@RestController
@RequestMapping(value="/focus")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleResourceService roleResourceService; 	
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="新增角色",notes="新增角色，需要 role_saveRole权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequestMapping(value="/role",method=RequestMethod.POST)
	@RequiresPermissions(value={"role_saveRole","administrator"},logical=Logical.OR)
	public Message<String> saveRole(RoleSaveVM role){
		roleService.saveRole(role);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="删除角色",notes="删除角色，需要role_deleteRole 权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequestMapping(value="/role/{roleId}",method=RequestMethod.DELETE)
	@RequiresPermissions(value={"role_deleteRole","administrator"},logical=Logical.OR)
	public Message<String> deleteRole(@PathVariable("roleId")String roleId){
		roleService.deleteRole(roleId);
		return Message.ok(roleId);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="修改角色",notes="修改角色，需要 role_updateRole权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"role_updateRole","administrator"},logical=Logical.OR)
	@RequestMapping(value="/role",method=RequestMethod.PUT)
	public Message<String>updateRole(RoleUpdateVM roleUpdateVM){
		roleService.updateRole(roleUpdateVM);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="查询角色列表",notes="查询角色列表，需要role_getRoleList权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"role_getRoleList","administrator"},logical=Logical.OR)
	@RequestMapping(value="/role",method=RequestMethod.GET)
	public Message<List<Role>>getRoleList(){
		List<Role> roleList=roleService.getRoleList();
		return Message.ok(roleList);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="给角色授权",notes="给角色授权，需要role_authorize权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"role_authorize","administrator"},logical=Logical.OR)
	@RequestMapping(value="/role/authorize",method=RequestMethod.POST)
	public  Message<String> authorize(RoleResourceVM roleResource){
		roleService.authorize(roleResource);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="校验code唯一性",notes="校验code唯一性，需要role_isExist权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"role_isExist","administrator"},logical=Logical.OR)
	@RequestMapping(value="/role/isExist",method=RequestMethod.GET)
	public Message<Boolean> isExist(@RequestParam(value="code")String code){
		Boolean isExist=roleService.isExist(code);
		return Message.ok(isExist);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="得到某角色拥有的资源",notes="校验code唯一性，需要role_isExist权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"role_getRoleResource","administrator"},logical=Logical.OR)
	@RequestMapping(value="/role/getRoleResource",method=RequestMethod.GET)
	public Message<List<String>>getRoleResource(String roleId){
		List<String >roleIds=new ArrayList<>();roleIds.add(roleId);
		List<String>list=roleResourceService.getResource(roleIds);
		return Message.ok(list);
	}
}
