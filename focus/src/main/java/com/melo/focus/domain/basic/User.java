package com.melo.focus.domain.basic;

import java.io.Serializable;

public class User implements Serializable {
    private String id;

    private String loginName;

    private String realName;

    private String password;
    
    private Boolean superman;
    
    public Boolean getSuperman() {
		return superman;
	}

	public void setSuperman(Boolean superman) {
		this.superman = superman;
	}

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