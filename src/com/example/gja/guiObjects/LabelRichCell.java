package com.example.gja.guiObjects;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LabelRichCell extends VerticalLayout implements ClickListener {


    private Label richText;
    Button b;

    private RichTextArea editor = new RichTextArea(); 

    public LabelRichCell(String text, Button edit) {
        setSpacing(true);
        editor = new RichTextArea(text);

        richText = new Label(text);
        richText.setContentMode(Label.CONTENT_XHTML);

        addComponent(richText);
        
        b = new Button("Edit");
        b.addListener(this);
        b.setVisible(false);
        addComponent(b);
        
        edit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				b.setVisible(!b.isVisible());
				
			}
		});

        editor.setWidth("100%");
    }

    public void buttonClick(ClickEvent event) {
        if (getComponentIterator().next() == richText) {
            editor.setValue(richText.getValue());
            replaceComponent(richText, editor);
        } else {
            richText.setValue(editor.getValue());
            replaceComponent(editor, richText);
        }
    }
   
    
    public Label getRichText() {
    	return richText;
    }

}
