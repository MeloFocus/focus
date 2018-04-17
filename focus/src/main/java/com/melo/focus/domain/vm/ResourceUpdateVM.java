package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
@ApiModel("修改资源VM")
public class ResourceUpdateVM {
	@NotBlank
	private String id;
	
	@ApiModelProperty(value="资源名")
	@NotBlank
    private String name;

	@NotNull
	@Max(3)
	@Min(1)
	@ApiModelProperty(value="资源类型")
    private Byte type;

	@NotBlank
	@ApiModelProperty(value="资源code码")
    private String code;
	
	@ApiModelProperty(value="路径")
	private String path;

	@ApiModelProperty(value="父资源code")
	@NotBlank
    private String parentCode;

	@ApiModelProperty(value="描述")
    private String des;

	@ApiModelProperty(value="序号")
	@NotNull
	@Max(99)
    private Byte sort;
	
	private String menuRoute;

	public String getMenuRoute() {
		return menuRoute;
	}

	public void setMenuRoute(String menuRoute) {
		this.menuRoute = menuRoute;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Byte getSort() {
		return sort;
	}

	public void setSort(Byte sort) {
		this.sort = sort;
	}

}
