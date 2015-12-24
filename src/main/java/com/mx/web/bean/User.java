package com.mx.web.bean;

import java.util.Date;

import org.hibernate.validator.constraints.Email;

public class User{

	private String id;
	
	private String username;
	
	@Email
	private String email;
	
	private String password;
	
	private Date createTime;

	public User() {}
	
	
	public User(String id, String username, String password, Date createTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createTime = createTime;
	}


	public User(String username, String password, Date createTime) {
		super();
		this.username = username;
		this.password = password;
		this.createTime = createTime;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.createTime = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
}
