package com.example.gja;

import com.example.gja.objects.Content;
import com.example.gja.objects.Content.ContentType;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;
import com.vaadin.ui.Window;

public class ShowContent extends Window {
	
	Image image;
	Audio audio;
	Video video;
	
	ThemeResource source;
	Label noContentSet = new Label("No content set for note.");
	
	
	public ShowContent (Content content) {
		
		setClosable(true);
		setResizable(false);
	    this.center();
	    
	    VerticalLayout subContent = new VerticalLayout();
	    this.setContent(subContent);
	    
	    if(content.getType().equals(ContentType.IMG)) {
	    	System.out.println("IMAGE");
	    	//Download content! Call ProcessRequest
			source = new ThemeResource(content.getValue());
	    	image = new Image();
	    	image.setSource(source);
	    	subContent.addComponent(image);
	    }
	    else if(content.getType().equals(ContentType.AUDIO)) {
	    	System.out.println("AUDIO");
	    	//Download content! Call ProcessRequest
			source = new ThemeResource(content.getValue());
	    	audio = new Audio();
	    	//ThemeResource nefunguje pro Audio..?!! TODO
	    	//audio.setSource(source);
	    	audio.setSource(new ExternalResource("http://www.stud.fit.vutbr.cz/~xkrylj00/GJA/record.MP3"));
	    	subContent.addComponent(audio);
	    }
	    else if(content.getType().equals(ContentType.VIDEO)) {
	    	System.out.println("VIDEO");
	    	//Download content! Call ProcessRequest
			source = new ThemeResource(content.getValue());
	    	video = new Video();
	    	video.setSource(source);
	    	subContent.addComponent(video);
	    }
	    else {
	    	subContent.addComponent(noContentSet);
	    	subContent.setWidth("220");
	    }
	}

}
