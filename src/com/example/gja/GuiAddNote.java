package com.example.gja;

import java.awt.TextField;
import java.util.LinkedList;

import com.example.gja.objects.Note;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class GuiAddNote extends Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Components
	protected Label nameLabel = new Label("Name:");
	protected Label commentsLabel = new Label("Comments:");
	protected TextArea name = new TextArea();
	protected TextArea comments = new TextArea();
	protected DateField remindsOn = new DateField();
	protected DateField expiresOn = new DateField();
	
	protected boolean reloadTable = false;
	
	Button addNote = new Button("Add Note");
	Button cancel = new Button("Cancel");

	GuiAddNote(LinkedList<Note> notes) {
		
		setClosable(false);
		setResizable(false);
		
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
	    this.setContent(subContent);
	    
	    //subContent.setHeight("600");
	    //subContent.setWidth("400");
	    
	    subContent.addComponent(nameLabel);
	    subContent.addComponent(name);
	    subContent.addComponent(commentsLabel);
	    subContent.addComponent(comments);
	    
	    remindsOn.setCaption("Reminds on");
	    
	    subContent.addComponent(remindsOn);
	    
	    expiresOn.setCaption("Expires on");
	    
	    subContent.addComponent(expiresOn);
	    
	    HorizontalLayout buttonLayout = new HorizontalLayout();
	    subContent.addComponent(buttonLayout);
	    buttonLayout.addComponent(addNote);
	    buttonLayout.addComponent(cancel);
	        
	    // Center it in the browser window
	    this.center();
	    
	    cancel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});    
	}

}
