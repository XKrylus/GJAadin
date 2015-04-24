package com.example.gja;

import javax.servlet.annotation.WebServlet;

import com.example.gja.logic.LoginProcess;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("gja")
public class GjaUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = GjaUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	protected Gui login = new Gui();
	protected GuiMain guiMain = new GuiMain();
	protected LoginProcess loginProcess = new LoginProcess();
	
	protected void processLogin() {
		login.buttonLogin.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(loginProcess.confirmUser(login.textFieldLogin.getValue(), login.textFieldPassword.getValue())) {
					setContent(guiMain);
				}
				else {
					login.textFieldLogin.setValue("Login failed");
					login.textFieldLogin.setValue("");
				}
			}
		});
	}

	@Override
	protected void init(VaadinRequest request) {
		
		setContent(login);
		processLogin();
	}

}