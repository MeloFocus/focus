package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.validation.constraints.NotNull;

@ApiModel(value="给角色授权")
public class RoleResourceVM {

	@ApiModelProperty("角色id集")
	@NotNull
	String roleIds;
	
	@ApiModelProperty("资源id集")
	@NotNull
	String resourceIds;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	
	
}
