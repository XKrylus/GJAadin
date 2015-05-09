package com.example.gja.objects;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User  implements IsSerializable {
	private String name;
	private String pwd;
	private long id;
	
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
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}