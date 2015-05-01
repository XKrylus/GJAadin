package com.example.gja;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.vaadin.tepi.listbuilder.ListBuilder;

import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import org.vaadin.tepi.listbuilder.*;

public class GuiAddNote extends Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Components
	protected Label titleLabel = new Label("Title:");
	protected Label descriptionLabel = new Label("Description:");
	protected TextArea title = new TextArea();
	protected TextArea description = new TextArea();
	protected DateField remindsOn = new DateField();
	protected DateField expiresOn = new DateField();
	protected TwinColSelect tagsListBuilder = new TwinColSelect();
	
	Upload content = new Upload();
	
	protected boolean reloadTable = false;
	
	Button addNote = new Button("Add Note");
	Button cancel = new Button("Cancel");

	GuiAddNote(LinkedList<Note> notes, ArrayList<Tag> tagsGlobal) {
		
		setClosable(false);
		setResizable(false);
		center();
		
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
	    this.setContent(subContent);
	    
	    //subContent.setHeight("600");
	    //subContent.setWidth("400");
	    
	    subContent.addComponent(titleLabel);
	    subContent.addComponent(title);
	    subContent.addComponent(descriptionLabel);
	    subContent.addComponent(description);
	    
	    remindsOn.setCaption("Reminds on");
	    remindsOn.setResolution(Resolution.SECOND);
	    subContent.addComponent(remindsOn);
	    
	    expiresOn.setCaption("Expires on");
	    expiresOn.setResolution(Resolution.SECOND);
	    subContent.addComponent(expiresOn);
	    
	    
	    
	    Container tagsContainer = new IndexedContainer();
	    tagsContainer.addContainerProperty("Tag", String.class, "none");
	    for(int i = 0; i < tagsGlobal.size(); i++) {
	    	tagsContainer.addItem(tagsGlobal.get(i).getValue());
	    }
	    
	    tagsListBuilder.setCaption("Tags");
	    tagsListBuilder.setContainerDataSource((Container) tagsContainer);
	    subContent.addComponent(tagsListBuilder);
	    
	    content.setCaption("Upload Content");
	    content.setButtonCaption("Upload file");
	    subContent.addComponent(content);
	    
	    HorizontalLayout buttonLayout = new HorizontalLayout();
	    subContent.addComponent(buttonLayout);
	    buttonLayout.addComponent(addNote);
	    buttonLayout.addComponent(cancel);
	    
	    
	    cancel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});    
	}

}
