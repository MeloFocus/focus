package com.melo.focus.domain.vm;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel("角色修改VM")
public class RoleUpdateVM {
	@NotBlank
	private String id;
	
	@NotBlank
    private String name;

	@NotBlank
	@Length(max=20)
    private String code;

	@NotNull
	@Max(99)
	@Min(1)
    private Byte sort;

	@Max(3)
	@Min(1)
    private Byte type;//

    private String des;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte getSort() {
		return sort;
	}

	public void setSort(Byte sort) {
		this.sort = sort;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
    
    
}
