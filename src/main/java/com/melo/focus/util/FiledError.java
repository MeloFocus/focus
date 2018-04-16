package com.melo.focus.util;

public class FiledError {

	private String message;
	
	private String beanName;
	
	private String filedName;

	
	@Override
	public String toString() {
		return "FiledError [message=" + message + ", beanName=" + beanName
				+ ", filedName=" + filedName + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public FiledError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiledError(String message, String beanName, String filedName) {
		super();
		this.message = message;
		this.beanName = beanName;
		this.filedName = filedName;
	}
	
	
}
