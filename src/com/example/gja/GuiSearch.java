package com.example.gja;

import java.util.ArrayList;
import java.util.Iterator;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.example.gja.objects.Category;
import com.example.gja.objects.Tag;
import com.vaadin.ui.Button.ClickEvent;

public class GuiSearch extends Window {
	
	Button cancel = new Button("Cancel");
	Button search = new Button("Search");
	
	TextArea searchFulltext = new TextArea();
	ComboBox searchCategory = new ComboBox();
	MenuBar searchTagsMain = new MenuBar();
	com.vaadin.ui.MenuBar.MenuItem searchTags = searchTagsMain.addItem("Tags", null);
	
	OptionGroup options = new OptionGroup("Select search method");
	
	public Category getCategory() {
		for (java.util.Iterator<String> i = (Iterator<String>) searchCategory.getItemIds().iterator(); i.hasNext();) {
    	    String iid = i.next();
    	    if (iid == searchCategory.getValue().toString()) {
    	    	return new Category(iid, null);
    	    }
    	}
		return null;
	}
	
	public ArrayList<Tag> getTags() {
		ArrayList<Tag> selectedTags = new ArrayList<Tag>();
		for (Iterator<MenuItem> i = searchTags.getChildren().iterator();i.hasNext();) {
    	    MenuItem iid = i.next();
    	    if(iid.isChecked())
    	    	selectedTags.add(new Tag(iid.toString()));
    	}
		return selectedTags;
	}
	
	public GuiSearch(ArrayList<Category> categoriesGlobal, ArrayList<Tag> tagsGlobal) {
		
		setClosable(false);
		setResizable(false);
	    this.center();
	    
	    VerticalLayout subContent = new VerticalLayout();
	    subContent.setMargin(true);
	    this.setContent(subContent);
	    
	    options.addItem("Fulltext");
	    options.addItem("Category");
	    options.addItem("Tags");
	    
	    for(int i = 0; i < categoriesGlobal.size(); i++) {
	    	searchCategory.addItem(categoriesGlobal.get(i).getName());
	    }
	    
	    for(int i = 0; i < tagsGlobal.size(); i++) {
	    	searchTags.addItem(tagsGlobal.get(i).getValue(), null).setCheckable(true);
	    }
	    
	    
	    options.setValue("Fulltext");
	    
	    subContent.addComponent(options);
	    subContent.addComponent(searchFulltext);
	    
	    options.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(event.getProperty().getValue() == "Fulltext")
					subContent.replaceComponent(subContent.getComponent(1), searchFulltext);
				else if(event.getProperty().getValue() == "Category")
					subContent.replaceComponent(subContent.getComponent(1), searchCategory);
				else if(event.getProperty().getValue() == "Tags")
					subContent.replaceComponent(subContent.getComponent(1), searchTagsMain);
			}
		});
	    
	    
	    HorizontalLayout buttonLayout = new HorizontalLayout();
	    subContent.addComponent(buttonLayout);
	    buttonLayout.addComponent(search);
	    buttonLayout.addComponent(cancel);
	    
	    cancel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
	}

}
