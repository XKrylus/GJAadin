package com.example.gja;

import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;

import com.example.gja.objects.Tag;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("deprecation")
public class GuiEditTags extends Window {
	
	Button addTag = new Button("Add Tag");
	Button editTag = new Button("Edit Tag");
	Button removeTag = new Button("Remove Tag");
	Button close = new Button("Close");
	TextField nameOfTag = new TextField();
	
	ListSelect tags = new ListSelect("List of tags");
	
	public void loadTags(ArrayList<Tag> tagsGlobal) {
		tags.removeAllItems();
	    for(int i = 0; i < tagsGlobal.size(); i++) {
	    	tags.addItem(tagsGlobal.get(i).getValue());
	    }
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuiEditTags(ArrayList<Tag> tagsGlobal) {
		
		setClosable(false);
		setResizable(false);
	    this.center();
		
		VerticalLayout subContent = new VerticalLayout();
	    this.setContent(subContent);
	 
	    loadTags(tagsGlobal);
	    subContent.addComponent(tags);
	    subContent.setComponentAlignment(tags, com.vaadin.ui.Alignment.MIDDLE_CENTER);
	    
	    HorizontalLayout buttons = new HorizontalLayout();
	    subContent.addComponent(buttons);
	    
	    buttons.addComponent(addTag);
	    buttons.addComponent(nameOfTag);
	    
	    subContent.addComponent(editTag);
	    subContent.addComponent(removeTag);
	    subContent.addComponent(close);
	    close.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				close();
				
			}
		});
	    
	    
	    
	    
		
	}

}
