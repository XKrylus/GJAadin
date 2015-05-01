package com.example.gja;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.example.gja.logic.LoginProcess;
import com.example.gja.objects.Category;
import com.example.gja.objects.Comment;
import com.example.gja.objects.Content;
import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;
import com.example.gja.objects.Note.state;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

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
					guiMain.currentUser = login.textFieldLogin.getValue();
					guiMain.topName.setValue(guiMain.currentUser);
				}
				else {
					login.textFieldLogin.setValue("Login failed");
					login.textFieldLogin.setValue("");
				}
			}
		});
	}
	
	protected void clickListeners() {
		guiMain.addNote.addClickListener(new Button.ClickListener() {
			/**
			 * ??!
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				GuiAddNote addNote = new GuiAddNote(guiMain.notes);
				addWindow(addNote);
				addNote.addNote.addClickListener(new Button.ClickListener() {
					
					
					@Override
					public void buttonClick(ClickEvent event) {
						state[] state = {null, null};
						ArrayList<Boolean> tags = new  ArrayList<Boolean>();
						ArrayList<Category> categories = new ArrayList<Category>();
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<Content> attachments = new ArrayList<Content>();
						
						guiMain.notes.addLast(new Note(addNote.title.getValue(), null, addNote.description.getValue(), guiMain.currentUser,
								addNote.remindsOn.getValue(), addNote.expiresOn.getValue(), state[0], tags, categories, comments, attachments));
						guiMain.loadTable(guiMain.notes);
						addNote.close();
					}
				}); 
			}
		});
		MenuBar.Command editTags = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				GuiEditTags editTags = new GuiEditTags(guiMain.tagsGlobal);
				addWindow(editTags);
				editTags.addTag.addClickListener(new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						guiMain.tagsGlobal.add(new Tag(editTags.nameOfTag.getValue()));
						editTags.loadTags(guiMain.tagsGlobal);
						guiMain.loadTable(guiMain.notes);
					}
				});
				editTags.editTag.addClickListener(new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
					}
				});
				editTags.removeTag.addClickListener(new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						int j = 0;
						 for (Iterator i = editTags.tags.getItemIds().iterator(); i.hasNext();)  {

					            Object iid = (Object) i.next();
					            if(editTags.tags.isSelected(iid))
					            {
					            	guiMain.tagsGlobal.remove(j);
					            }
					            j++;
					        }
						 editTags.loadTags(guiMain.tagsGlobal);
						 guiMain.loadTable(guiMain.notes);
					}
				});
			}
		};
		
		guiMain.editTags.setCommand(editTags);
	}
	

	@Override
	protected void init(VaadinRequest request) {
		
		setContent(login);
		clickListeners();
		processLogin();
	}

}