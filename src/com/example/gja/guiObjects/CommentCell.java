package com.example.gja.guiObjects;

import java.util.ArrayList;

import com.example.gja.objects.Comment;
import com.example.gja.objects.Content;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CommentCell extends VerticalLayout implements ClickListener {

	private TextArea newComment;
	private ArrayList<Comment> comment = new ArrayList();
	private com.vaadin.ui.MenuBar.MenuItem commentOpen;
    private Button b;
    private Button c;
    
    private int identifier;
    
    private MenuBar commentBar;
    
    private void loadCommentTable() {
    	commentOpen.removeChildren();
    	for(int j = 0; j < comment.size(); j++) {
    		commentOpen.addItem(comment.get(j).getCreated() + ": " + comment.get(j).getValue(), null).setCheckable(false);
    	}
    }
    
    private void enableChecking(boolean checkable) {
    	//Remove checks
    	for(int j = 0; j < comment.size(); j++) {
    		commentOpen.getChildren().get(j).setChecked(false);;
    	}
    	//Enable/disable checking
    	for(int j = 0; j < comment.size(); j++) {
    		commentOpen.getChildren().get(j).setCheckable(checkable);
    	}
    }

    public CommentCell(ArrayList<Comment> commentInput, Button edit, int i) {
    	
    	identifier = i;
    	//comment = commentInput;
    	for(int j = 0; j < commentInput.size(); j++) {
    		comment.add(commentInput.get(j));
    	}
        setSpacing(true);
        
        MenuBar commentSpace = new MenuBar();
        commentOpen = commentSpace.addItem("Comments", null);
        loadCommentTable();
        addComponent(commentSpace);
        setComponentAlignment(commentSpace, Alignment.BOTTOM_CENTER);
        
        newComment = new TextArea("Comment text");
        newComment.setHeight("50");
        newComment.setWidth("150");
        newComment.setVisible(false);
        addComponent(newComment);
        
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setVisible(false);
        addComponent(buttons);
        b = new Button("Add");
        b.addListener(this);
        buttons.addComponent(b);
        c = new Button("Remove");
        c.addListener(this);
        buttons.addComponent(c);
        
        
        edit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				buttons.setVisible(!buttons.isVisible());
				newComment.setVisible(!newComment.isVisible());
				enableChecking(newComment.isVisible());
			}
		});
    }

	@Override
	public void buttonClick(ClickEvent event) {
		//Add comment
		if(event.getButton() == b) {
		this.comment.add(new Comment("bena", newComment.getValue()));
		loadCommentTable();
		newComment.setValue("");
		}
		//Remove comment
		else if(event.getButton() == c) {
			for(int i = 0; i < comment.size(); i++) {
				if(commentOpen.getChildren().get(i).isChecked()) {
					this.commentOpen.removeChild(commentOpen.getChildren().get(i));
					this.comment.remove(i);
					i = 0;
				}
			}
		}
		System.out.println("Clicked.");
	}
	
	public ArrayList getCommentList() {
		return this.comment;
	}
	
	public int getIdentifier() {
		return this.identifier;
	}
}
