package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("新增用户角色关系VM")
public class UserRoleSaveVM {
	
	@ApiModelProperty(value = "用户id")
    private String userId;

	@ApiModelProperty(value = "角色id")
    private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	//这里不需要有业务，新增业务角色应该单独写接口
	//@ApiModelProperty(value = "业务角色id")
    //private String ywId;
	
	
}
