package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.NotBlank;
@ApiModel("修改用户VM")
public class UserUpdateVM {
	
	@ApiModelProperty(value = "id")
	@NotBlank
    private String id;

	@ApiModelProperty(value = "用户登录名")
	@NotBlank
    private String loginName;

	@ApiModelProperty(value = "用户真实姓名")
	@NotBlank
    private String realName;

	@ApiModelProperty(value = "密码")
	@NotBlank
    private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
