package com.example.gja.guiObjects;

import java.util.ArrayList;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Label.ValueChangeEvent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.example.gja.objects.Category;
import com.ibm.icu.util.BytesTrie.Iterator;

@SuppressWarnings("serial")
public class LabelRichCell extends VerticalLayout implements ClickListener {


    private Label richText;
    private static String noCategoty = null;
    Button b;
    Button c;

    private RichTextArea editor = new RichTextArea();
    public ComboBox selectCategory = new ComboBox();
    private BeanItemContainer<String> container = new BeanItemContainer<String>(String.class);
    
    private void loadContainer(ArrayList<Category> categoriesGlobal) {
    	for(int i = 0; i < categoriesGlobal.size(); i++) {
    		container.addItem(categoriesGlobal.get(i).getName());
    	}
    }

    private String loadCategory(Category category) {
    	for (java.util.Iterator<String> i = container.getItemIds().iterator(); i.hasNext();) {
    	    String iid = i.next();
    	    if (iid == category.getName()) {
    	    	return iid;
    	    }
    	}
    	return null;
    }
    
    public LabelRichCell(String text, Category category, ArrayList<Category> categoriesGlobal, Button edit) {
        setSpacing(true);
        editor = new RichTextArea(text);

        richText = new Label(text);
        richText.setContentMode(Label.CONTENT_XHTML);

        addComponent(richText);
        
        loadContainer(categoriesGlobal);
        selectCategory.setContainerDataSource(container);
        
        if(!(category.getName() == noCategoty)) {
        	selectCategory.setValue(loadCategory(category));
        }
        
        b = new Button("Edit text");
        b.addListener(this);
        b.setVisible(false);
        addComponent(b);
        
        c = new Button("Edit category");
        c.addListener(this);
        c.setVisible(false);
        addComponent(c);
        
        edit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				b.setVisible(!b.isVisible());
				c.setVisible(b.isVisible());
				
			}
		});

        editor.setWidth("100%");
    }

    public void buttonClick(ClickEvent event) {
    	Object x = getComponentIterator().next();
    	if(event.getButton() == b) {
    		if (x == richText) {
                editor.setValue(richText.getValue());
                replaceComponent(richText, editor);
            } else {
                richText.setValue(editor.getValue());
                replaceComponent(editor, richText);
            }
    	}
    	else {
    		if (x == richText) {
                replaceComponent(richText, selectCategory);
            } else {
                replaceComponent(selectCategory, richText);
            }
    	}
    }
    
    public Category getCategory() {
    	return new Category((String) selectCategory.getValue(), null);
    }
   
    
    public Label getRichText() {
    	return richText;
    }

}
