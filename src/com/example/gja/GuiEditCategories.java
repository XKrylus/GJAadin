package com.example.gja;

import java.util.ArrayList;

import com.example.gja.objects.Category;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class GuiEditCategories extends Window {
	
	Button addCategory = new Button("Add Category");
	Button removeCategory = new Button("Remove Category");
	Button close = new Button("Close");
	TextField nameOfCategory = new TextField();
	
	ListSelect categories = new ListSelect("List of categories");
	
	public void loadCategories(ArrayList<Category> categoriesGlobal) {
		categories.removeAllItems();
	    for(int i = 0; i < categoriesGlobal.size(); i++) {
	    	categories.addItem(categoriesGlobal.get(i).getName());
	    }
	}
	
	public GuiEditCategories(ArrayList<Category> categoriesGlobal) {
		
		setClosable(false);
		setResizable(false);
	    this.center();
	    
	    VerticalLayout subContent = new VerticalLayout();
	    this.setContent(subContent);
	    
	    loadCategories(categoriesGlobal);
	    subContent.addComponent(categories);
	    subContent.setComponentAlignment(categories, com.vaadin.ui.Alignment.MIDDLE_CENTER);
	    
	    HorizontalLayout buttons = new HorizontalLayout();
	    subContent.addComponent(buttons);
	    
	    buttons.addComponent(addCategory);
	    buttons.addComponent(nameOfCategory);
	    
	    buttons.addComponent(removeCategory);
	    subContent.addComponent(close);
	    close.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				close();
				
			}
		});
		
	}

}
