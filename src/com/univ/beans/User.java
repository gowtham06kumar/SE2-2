package com.univ.beans;

public class User {
	
	private String userName;
	private String passwd;
	
	public User()
	{}
	public User(String value1, String value2) {
		this.userName = value1;
		this.passwd = value2;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
