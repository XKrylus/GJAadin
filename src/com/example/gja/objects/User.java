package com.example.gja.objects;

public class User {
	private String name;
	private String pwd;
	
	public User(String name, String pwd){
		this.name = name;
		this.pwd = pwd;
	}
	
	public boolean checkPwd(String pwd){
		return this.pwd.equals(pwd);
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean setPwd(String oldPwd, String newPwd){
		if(this.pwd.equals(oldPwd)){
			this.pwd = newPwd;
			return true;
		} else {
			return false;
		}
	}
}