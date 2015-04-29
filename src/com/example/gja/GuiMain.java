package com.example.gja;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.openqa.selenium.remote.html5.AddWebStorage;

import com.example.gja.objects.Category;
import com.example.gja.objects.Comment;
import com.example.gja.objects.Content;
import com.example.gja.objects.Note;
import com.example.gja.objects.Note.state;
import com.example.gja.objects.Tag;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class GuiMain  extends VerticalLayout{
	
	//Current user
	public String currentUser = "dev";
	
	//TopMenu Components
	Label topFlag = new Label("Flag");
	Label topName = new Label(currentUser);
	Label gap = new Label();
	Button addNote = new Button();
	MenuBar topMenuBar = new MenuBar();
	
	//List with Notes
	LinkedList<Note> notes = new LinkedList<Note>();
	
	//Table
	Table table = new Table("Notes table");
	
	/**
	 * ??!
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	protected void loadTable(LinkedList<Note> notes) {
		table.removeAllItems();
		for(int i = 0; i < notes.size(); i++) {
			//table.addItem(new Object[]{, , });
			
			Label title = new Label(notes.get(i).getTitle());
			Label Content = null;//new Label(notes.get(i).getContent().getValue());
			TextArea Description = new TextArea();
			Description.setValue(notes.get(i).getDescription());
			
			Date remindOn = notes.get(i).getReminder();
			Date inputExpire = notes.get(i).getExpire();
			
			Label state = null;//new Label(notes.get(i).getState().toString());
			
			table.addItem(new Object[]{title, Content, Description, remindOn, inputExpire, state}, i);
		}
	}
	
	protected HorizontalLayout topMenuInit(HorizontalLayout topMenu) {
		//Top Menu Components init
		topMenu.addComponent(topFlag);
		topMenu.addComponent(topName);
		gap.setWidth("100%");
		topMenu.addComponent(gap);
		addNote.setCaption("+");
		topMenu.addComponent(addNote);
		topMenu.setComponentAlignment(addNote, Alignment.MIDDLE_RIGHT);
		
		
		topMenu.addComponent(topMenuBar);
		topMenu.setComponentAlignment(topMenuBar, Alignment.MIDDLE_RIGHT);
		com.vaadin.ui.MenuBar.MenuItem topMenuBarOptions = topMenuBar.addItem("Options", null, null);
		topMenuBarOptions.addItem("Search", null, null);
		topMenuBarOptions.addItem("Sync", null, null);
		topMenuBarOptions.addItem("Flag", null, null);
		topMenuBarOptions.addItem("Confirm", null, null);
		
		return topMenu;
	}
	
	/*public Note(String title, Content content, String desc, String user, Date inputReminder, Date inputExpire, state state, ArrayList<Tag> tags,
	ArrayList<Category> categories, ArrayList<Comment> comments, ArrayList<Content> attachments);*/
	
	protected Table tableInit(Table table) {
		
		//Table init
		table.setWidth("100%");
		table.setHeight("100%");
		table.addStyleName("components-inside");
		table.setSelectable(true);
		
		table.addContainerProperty("Title",           Label.class,     null);
		table.addContainerProperty("Content",         Label.class,     null);
		table.addContainerProperty("Description",     TextArea.class,     null);
		
		table.addContainerProperty("Remind on",       Date.class,    null);
		table.addContainerProperty("Input Expire",    Date.class,    null);
		
		table.addContainerProperty("State",           Label.class, null);
		return table;
	}

	public GuiMain() {
		
		//this.addComponent(new Label("Hello Bena!"));
		this.setSizeFull();
		
		//TopMenu init
		HorizontalLayout topMenu = new HorizontalLayout();
		this.addComponent(topMenu);
		topMenu.setWidth("100%");
		topMenu.setHeight(Float.toString(this.getHeight() / 5));
		
		//Init Top Menu (add components)
		topMenuInit(topMenu);
		
		//Notes
		VerticalLayout notesSpace = new VerticalLayout();
		notesSpace.setSizeFull();
		this.addComponent(notesSpace);
		
		//Init Table
		tableInit(table);
		notesSpace.addComponent(table);
		
		/*//Label current (selected)
		final Label current = new Label("Selected: -");
		notesSpace.addComponent(current);
		
		table.addValueChangeListener(new Property.ValueChangeListener() {


			@Override
		    public void valueChange(ValueChangeEvent event) {
		        current.setValue("Selected: " + table.getValue());
		    }

		});*/
		
		//Initial notes
		String[] title = {"Nakup", "Prodej"};
		Content[] content = {null, null};
		String[] description = {"Mleko\n" +  "4 rohliky", "Vrtacka\n" + "Bena"};
		Date[] inputReminder = {new Date(), new Date()};
		Date[] inputExpire = {new Date(), new Date()};
		state[] state = {null, null};
		ArrayList<Tag> tags = new  ArrayList<Tag>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		ArrayList<Content> attachments = new ArrayList<Content>();
		
		for(int i = 0; i < title.length; i++) {
			notes.add(new Note(title[i], content[i], description[i], currentUser, inputReminder[i], inputExpire[i], state[i], tags, categories, comments, attachments));
		}
		
		//Load initial notes to table
		loadTable(notes);
		
	}
	
	

}
