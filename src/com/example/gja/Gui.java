package com.example.gja;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { � }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Gui extends VerticalLayout {
	protected com.vaadin.ui.Image imageAppIcon;
	protected com.vaadin.ui.TextField textFieldLogin;
	protected com.vaadin.ui.PasswordField textFieldPassword;
	protected com.vaadin.ui.Label labelInfo;
	protected com.vaadin.ui.Button buttonLogin;
	protected com.vaadin.ui.Button buttonRegister;

	public Gui() {
		Design.read(this);
	}
}
