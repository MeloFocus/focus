package com.melo.focus.util;

public class Message<T> {

	private Integer status;
	
	private String code;
	
	private String message;
	
	private T data;

	public static <T>Message<T> ok(T data){
		return new Message<T>(data);
	}
	
	public static <T>Message<T> ok(T data,Integer status){
		return new Message<T>(data,status);
	}
	
	
	public Message() {
	}

	public Message(T data,Integer status){
		this.status=status;
		this.data=data;
		this.code = "ok";
		this.message = "成功";
	}
	
	public Message(T data) {
		this.status = 200;
		this.code = "ok";
		this.message = "成功";
		this.data = data;
	}
	public Message(Integer status, String code, String message, T data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
