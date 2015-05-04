package com.example.gja.logic;

public class LoginProcess {
	
	ProcessRequest request = new ProcessRequest();
	
	public boolean confirmUser(String userName, String password) {
		if(request.userLogin(userName, password)) return true;
		else return false;
	}
	
	public boolean confirmRegister(String userName, String password) {
		if(request.userRegister(userName, password)) return true;
		else return false;
	}
	
	public void confirmLogout(String userName) {
		request.userLogout(userName);
	}
	
	public LoginProcess() {
		
	}
}
