package com.example.gja;

import com.example.gja.objects.Content;
import com.example.gja.objects.Content.ContentType;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;
import com.vaadin.ui.Window;

public class ShowContent extends Window {
	
	Image image;
	Audio audio;
	Video video;
	
	ThemeResource source;
	
	
	public ShowContent (Content content) {
		
		//Download content! Call ProcessRequest
		source = new ThemeResource(content.getValue());
		System.out.printf("CONTENT SOURCE: %s\n", content.getValue());
		
		setClosable(true);
		setResizable(false);
	    this.center();
	    
	    VerticalLayout subContent = new VerticalLayout();
	    this.setContent(subContent);
	    
	    if(content.getType().equals(ContentType.IMG)) {
	    	System.out.println("IMAGE");
	    	image = new Image();
	    	image.setSource(source);
	    	subContent.addComponent(image);
	    }
	    else if(content.getType().equals(ContentType.AUDIO)) {
	    	System.out.println("AUDIO");
	    	audio = new Audio();
	    	//ThemeResource nefunguje pro Audio..?!! TODO
	    	//audio.setSource(source);
	    	audio.setSource(new ExternalResource("http://www.stud.fit.vutbr.cz/~xkrylj00/GJA/record.MP3"));
	    	subContent.addComponent(audio);
	    }
	    else if(content.getType().equals(ContentType.VIDEO)) {
	    	System.out.println("VIDEO");
	    	video = new Video();
	    	video.setSource(source);
	    	subContent.addComponent(video);
	    }
	}

}
