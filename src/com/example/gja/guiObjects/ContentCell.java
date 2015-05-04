package com.example.gja.guiObjects;

import com.example.gja.GjaUI;
import com.example.gja.GuiMain;
import com.example.gja.ShowContent;
import com.example.gja.objects.Content;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ContentCell extends VerticalLayout implements ClickListener {

    Button b;
    Upload upload;
    
    ThemeResource contentPlaceholder = new ThemeResource("images/GJAlogo.png");
	ThemeResource contentImg = new ThemeResource("images/image.png");
	ThemeResource contentVideo = new ThemeResource("images/video.png");
	ThemeResource contentAudio = new ThemeResource("images/audio.png");
	ThemeResource contentNotSet = new ThemeResource("images/cancel.png");

    public ContentCell(Content content, Button edit, GjaUI parentUI, GuiMain main, int iterator) {
        setSpacing(true);
        
        ThemeResource imageResource;
		Embedded contentEmbedded;
		switch(content.getType()) {
		case IMG :
			imageResource = contentImg;
			break;
		case VIDEO :
			imageResource = contentVideo;
			break;
		case AUDIO  :
			imageResource = contentAudio;
			break;
		default :
			imageResource = contentNotSet;
			break;
		}
		//imageResource = contentPlaceholder;
		contentEmbedded = new Embedded(null, imageResource);
		contentEmbedded.setDescription(String.valueOf(iterator));
		contentEmbedded.addClickListener(new MouseEvents.ClickListener() {
			
			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
					//System.out.printf("CALLED FROM: %s\n", content.getDescription());
					if(!upload.isVisible()) {
					main.request.setContent(main.notes.get(Integer.valueOf(contentEmbedded.getDescription())).getContent(),
							main.notes.get(Integer.valueOf(contentEmbedded.getDescription())));
					//System.out.printf("Now downloading file for note %d\n", Integer.valueOf(content.getDescription()));
					parentUI.addWindow(main.showContent = new ShowContent(main.notes.get(Integer.valueOf(contentEmbedded.getDescription())).getContent()));
					}
			}
		});
		addComponent(contentEmbedded);
        
        //addComponent(contentIMG);
        upload = new Upload();
        upload.setButtonCaption("New Content");
        upload.setVisible(false);
        addComponent(upload);
        
        edit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				upload.setVisible(!upload.isVisible());
				
			}
		});
    }

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("Clicked.");
	}
}
