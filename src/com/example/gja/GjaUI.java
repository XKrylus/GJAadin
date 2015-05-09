package com.example.gja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import com.example.gja.logic.LoginProcess;
import com.example.gja.logic.ProcessRequest;
import com.example.gja.objects.Category;
import com.example.gja.objects.Comment;
import com.example.gja.objects.Content;
import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;
import com.example.gja.objects.Content.ContentType;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
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
	@VaadinServletConfiguration(productionMode = false, ui = GjaUI.class, widgetset = "com.example.gja.widgetset.GjaWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	
	//PLACEHOLDER: ID OF NOTE
	long ID = 0;
	
	protected Gui login = new Gui();
	protected GuiMain guiMain;
	protected ProcessRequest request = new ProcessRequest();
	protected LoginProcess loginProcess = new LoginProcess();
	protected ServletContext servletContext;
	
	private static String loginFailed = "Login Failed";
	private static String loginRegistered = "User Registered";
	
	protected void processLogin() {
		//login.buttonLogin.setStyleName("toplink");
		login.buttonLogin.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(loginProcess.confirmUser(login.textFieldLogin.getValue(), login.textFieldPassword.getValue())) {
					guiMain = new GuiMain(GjaUI.this);
					clickListeners();
					setContent(guiMain);
					guiMain.currentUser = login.textFieldLogin.getValue();
					guiMain.topName.setValue(guiMain.currentUser);
				}
				else {
					login.labelInfo.setValue(loginFailed);
					login.textFieldPassword.setValue("");
				}
			}
		});
		
		login.buttonRegister.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(loginProcess.confirmRegister(login.textFieldLogin.getValue(), login.textFieldPassword.getValue())) {
					login.labelInfo.setValue(loginRegistered);
					login.textFieldLogin.setValue("");
					login.textFieldPassword.setValue("");
				}
				else {
					login.labelInfo.setValue("User Already Exists");
					login.textFieldLogin.setValue("");
					login.textFieldPassword.setValue("");
				}
			}
		});
	}
	
	protected void clickListeners() {
		
		guiMain.logout.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				loginProcess.confirmLogout(guiMain.currentUser);
				guiMain.currentUser = null;
				login.labelInfo.setValue(null);
				login.textFieldLogin.setValue("");
				login.textFieldPassword.setValue("");
				setContent(login);
			}
		});
		
		//Add note window
		guiMain.addNote.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				GuiAddNote addNote = new GuiAddNote(guiMain.notes, guiMain.tagsGlobal);
				addWindow(addNote);
				addNote.addNote.addClickListener(new Button.ClickListener() {
					
					
					//Add note button - adding note with options set in window
					@Override
					public void buttonClick(ClickEvent event) {
						ArrayList<Tag> tags = new  ArrayList<Tag>();
						Content[] content = {new Content(ContentType.NONE, null)};
						String currentString = addNote.tagsListBuilder.getValue().toString();
						currentString = currentString.replaceAll("\\[\\(\\)\\]", "");
						currentString = currentString.replaceAll("\\[", "");
						currentString = currentString.replaceAll("\\]", "");
						String parts[] = currentString.split(", ");
						for(int i = 0; i < guiMain.tagsGlobal.size(); i++) {
							for(int j = 0; j < parts.length; j++) {
								if(guiMain.tagsGlobal.get(i).getValue().equals(parts[j])) {
									tags.add(guiMain.tagsGlobal.get(i));
								}
							}
						}
						Category category = null;
						ArrayList<Comment> comments = new ArrayList<Comment>();
						ArrayList<Content> attachments = new ArrayList<Content>();
						
						//UPLOAD NOTE
						Note note = new Note(addNote.title.getValue(), content[0], addNote.description.getValue(), guiMain.currentUser,
								addNote.remindsOn.getValue(), addNote.expiresOn.getValue(), tags, category, comments, attachments);
						note.setId(request.uploadNote(guiMain.currentUser, note));
						
						guiMain.notes.addLast(note);
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
						// SERVER - Tag added - update on server
						Tag tag = new Tag(editTags.nameOfTag.getValue());
						tag.setId(request.addTag(guiMain.currentUser, tag));
						guiMain.tagsGlobal.add(tag);
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
					            	// SERVER - Tag removed - update on server
							        request.removeTag(guiMain.currentUser, guiMain.tagsGlobal.get(j));
							      //LOCAL - REMOVE OR REMAIN?!!
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
		
		MenuBar.Command search = new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				GuiSearch search = new GuiSearch(guiMain.categoriesGlobal, guiMain.tagsGlobal);
				addWindow(search);
				
				search.search.addClickListener(new Button.ClickListener() {
					
					//ODKOMENTOVAT!
					
					@Override
					public void buttonClick(ClickEvent event) {
						if(search.options.getValue() == "Fulltext") {
							guiMain.notes = request.notesDownloadFulltext(guiMain.currentUser, search.searchFulltext.getValue());
							guiMain.loadTable(guiMain.notes);
							search.close();
						}
						else if(search.options.getValue() == "Category") {
							guiMain.notes = request.notesDownloadCategory(guiMain.currentUser, ID);
							guiMain.loadTable(guiMain.notes);
							//search.close();
						}
						else if(search.options.getValue() == "Tags") {
							guiMain.notes = request.notesDownloadTag(guiMain.currentUser, search.getTags());
							guiMain.loadTable(guiMain.notes);
							search.close();
						}
					}
				});
				
			}
			
		};
		
		guiMain.search.setCommand(search);
		
		MenuBar.Command editCategories = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				GuiEditCategories editCategories = new GuiEditCategories(guiMain.categoriesGlobal);
				addWindow(editCategories);
				editCategories.addCategory.addClickListener(new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						Category category = new Category(editCategories.nameOfCategory.getValue(), "");
						guiMain.categoriesGlobal.add(category);
						// SERVER - Tag removed - update on server
				        request.addCategory(guiMain.currentUser, category);
						editCategories.loadCategories(guiMain.categoriesGlobal);
						guiMain.loadTable(guiMain.notes);
					}
				});
				editCategories.removeCategory.addClickListener(new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						int j = 0;
						 for (Iterator i = editCategories.categories.getItemIds().iterator(); i.hasNext();)  {

					            Object iid = (Object) i.next();
					            if(editCategories.categories.isSelected(iid))
					            {
					            	// SERVER - Category removed - update on server
							        request.removeCategory(guiMain.currentUser, guiMain.categoriesGlobal.get(j));
							        //LOCAL - REMOVE OR REMAIN?!!
							        guiMain.categoriesGlobal.remove(j);
							        editCategories.loadCategories(guiMain.categoriesGlobal);
							        //Removing of notes under category handled by server
							        guiMain.notes = request.notesDownloadAll(guiMain.currentUser);
							        guiMain.loadTable(guiMain.notes);
							        break;
					            }
					            j++;
					        }
						 //editCategories.loadCategories(guiMain.categoriesGlobal);
						 //guiMain.loadTable(guiMain.notes);
					}
				});
			}
		};
		
		guiMain.editCategories.setCommand(editCategories);
		
		MenuBar.Command synchronize = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				guiMain.tagsGlobal = request.getTags(guiMain.currentUser);
				guiMain.categoriesGlobal = request.getCategories(guiMain.currentUser);
				guiMain.notes = request.notesDownloadAll(guiMain.currentUser);
				guiMain.loadTable(guiMain.notes);
			}
		};
		
		guiMain.sync.setCommand(synchronize);
		
		
	}
	

	@Override
	protected void init(VaadinRequest request) {
		
		setContent(login);
		login.imageAppIcon.setIcon(new ThemeResource("images/GJAlogo.png"));
		login.textFields.setMargin(true);
		processLogin();
		
		servletContext = VaadinServlet.getCurrent().getServletContext();
	}

}