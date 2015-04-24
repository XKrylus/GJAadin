package com.example.gja.logic;

public class LoginProcess {
	
	public boolean confirmUser(String user, String passwd) {
		if(user.equals("Bena") && passwd.equals("bewna")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public LoginProcess() {
		
	}
}
