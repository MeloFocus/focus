package com.melo.focus.domain.basic;

public class Resource {
    private String id;

    private String name;

    private Byte type;

    private String code;

    private String pid;

    private String des;

    private Byte sort;
    
    private String path;//path可以方便的查 某个节点下的所有数据，省去递归，但树封装还得自己做。
    
    private String menuRoute;
    
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
}