package com.melo.focus.controller;

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

import com.melo.focus.domain.basic.Authority;
import com.melo.focus.domain.vm.AuthoritySaveVM;
import com.melo.focus.domain.vm.AuthorityUpdateVM;
import com.melo.focus.domain.vm.AuthorityVMS;
import com.melo.focus.service.AuthorityService;
import com.melo.focus.util.Constants;
import com.melo.focus.util.Message;

@RestController
@RequestMapping(value="/focus")
public class AuthorityController {
	
	@Autowired
	AuthorityService authorityService;	

	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="按controller分组，得到authority列表",notes="按controller分组，得到authority列表，需要authority_getAuthority权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"authority_getAuthority","administrator"},logical=Logical.OR)
	@RequestMapping(value="/authority",method=RequestMethod.GET)
	public Message<List<AuthorityVMS>> getAuthority(){
		List<AuthorityVMS> list=authorityService.getAuthority();
		return Message.ok(list);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header"),
		@ApiImplicitParam(name = "method", value = "请求方式", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "word", value = "关键字", dataType = "string", paramType = "query")
	})
	@ApiOperation(value="根据请求方式，按关键字模糊查询",notes="根据请求方式，按关键字模糊查询，需要authority_getLikeAuthority权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"authority_getLikeAuthority","administrator"},logical=Logical.OR)
	@RequestMapping(value="/getLikeAuthority",method=RequestMethod.GET)
	public Message<List<Authority>> getLikeAuthority(@RequestParam("method")String method,@RequestParam("word")String word){
		List<Authority> list=authorityService.getLikeAuthority(method,word);
		return Message.ok(list);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="新增操作码",notes="新增操作码，需要authority_saveAuthority权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"authority_saveAuthority","administrator"},logical=Logical.OR)
	@RequestMapping(value="/authority",method=RequestMethod.POST)
	public Message<String> saveAuthority(@RequestBody AuthoritySaveVM authoritySaveVM){
		authorityService.saveAuthority(authoritySaveVM);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="修改操作码",notes="修改操作码，需要authority_updateAuhtority权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"authority_updateAuhtority","administrator"},logical=Logical.OR)
	@RequestMapping(value="/authority",method=RequestMethod.PUT)
	public Message<String> updateAuhtority(@RequestBody AuthorityUpdateVM authorityUpdateVM){
		authorityService.updateAuhtority(authorityUpdateVM);
		return Message.ok(Constants.SUCCESS);
	}
	
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
	})
	@ApiOperation(value="删除操作码",notes="删除操作码，需要authority_deleteAuthority权限或管理员权限")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@RequiresPermissions(value={"authority_deleteAuthority","administrator"},logical=Logical.OR)
	@RequestMapping(value="/authority/{id}",method=RequestMethod.DELETE)
	public Message<String> deleteAuthority(@PathVariable("id")String id){
		authorityService.deleteAuthority(id);
		return Message.ok(Constants.SUCCESS);
	}
}
