package com.melo.focus.domain.vm;

import java.util.List;

import com.melo.focus.domain.basic.Authority;

public class AuthorityVMS {

	private String controller;
	
	List<Authority> authorityList;

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
	
}
