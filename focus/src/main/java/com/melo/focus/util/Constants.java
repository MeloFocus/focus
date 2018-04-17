package com.melo.focus.util;

public class Constants {

	public static final String SUCCESS="SUCCESS";
	
	public static final String REDIS_RESOURCE="REDIS_RESOURCE";
	
	public static final String REDIS_RESOURCE_TREE="REDIS_RESOURCE_TREE";
	
	public enum RoleType{
		initial("内置角色",1),
		custom("自定义角色",2),
		business("业务角色",3);
		
		private String name;
		private int value;
		
		public String getName() {
			return name;
		}
		public int getValue() {
			return value;
		}

		private RoleType(String name, int value) {
			this.name = name;
			this.value = value;
		}
	}
	
	public enum ResourceType{
		module("模块",1),
		column("栏目",2),
		button("按钮",3);
		
		private String name;
		private int value;
		public String getName() {
			return name;
		}
		public int getValue() {
			return value;
		}
		private ResourceType(String name, int value) {
			this.name = name;
			this.value = value;
		}
		
	}
}
