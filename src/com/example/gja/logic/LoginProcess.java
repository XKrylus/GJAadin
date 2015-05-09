package com.example.gja.logic;

public class LoginProcess {
	
	ProcessRequest request = new ProcessRequest();
	
	public boolean confirmUser(String userName, String password) {
		if(request.userLogin(userName, password)) return true;
		else return false;
	}
	
	public boolean confirmRegister(String userName, String password) {
		request.userRegister(userName, password); 
		return true;
	}
	
	public void confirmLogout(String userName) {
	}
	
	public LoginProcess() {
		
	}
}
