package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;

import org.hibernate.validator.constraints.NotBlank;

@ApiModel("批量新增用户角色关系VM")
public class UserRoleSaveVMS {

	@NotBlank
	private String userId;
	@NotBlank
	private String roleIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
	
	
}
