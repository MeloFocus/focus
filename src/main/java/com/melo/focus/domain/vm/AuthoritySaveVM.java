package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.NotBlank;

@ApiModel("新增权限VM")
public class AuthoritySaveVM {
	
	@ApiModelProperty("名称")
	@NotBlank
    private String name;

	@ApiModelProperty("操作码")
	@NotBlank
    private String code;

	@ApiModelProperty("url")
	@NotBlank
    private String url;

	@ApiModelProperty("请求方式")
	@NotBlank
    private String method;

	@ApiModelProperty("controller")
	@NotBlank
    private String controller;

	@ApiModelProperty("描述")
    private String des;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
    
    
}
