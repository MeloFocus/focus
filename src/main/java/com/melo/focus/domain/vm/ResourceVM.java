package com.melo.focus.domain.vm;

import java.util.List;

public class ResourceVM {
	
	private String id;

    private String name;

    private Byte type;

    private String code;

    private String pid;

    private String des;

    private Byte sort;
    
    private String path;
    
	private String menuRoute;
    
    private List<ResourceVM>children;

	public String getMenuRoute() {
		return menuRoute;
	}

	public void setMenuRoute(String menuRoute) {
		this.menuRoute = menuRoute;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public List<ResourceVM> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceVM> children) {
		this.children = children;
	}
    
    

}
