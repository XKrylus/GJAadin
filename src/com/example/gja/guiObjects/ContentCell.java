package com.example.gja.guiObjects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;

import com.example.gja.GjaUI;
import com.example.gja.GuiMain;
import com.example.gja.ShowContent;
import com.example.gja.logic.ProcessRequest;
import com.example.gja.objects.Content;
import com.example.gja.objects.Content.ContentType;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContentCell extends VerticalLayout implements ClickListener {
	
	class ImageUploader implements Receiver, SucceededListener {
	    public File file;
	    
	    
	    public ImageUploader() {
	    	
	    }
	    
	    public OutputStream receiveUpload(String filename,
	                                      String mimeType) {
	        FileOutputStream fos = null; // Stream to write to
	        try {
	        	//Remove old file - TODO!
	        	File folder;
	        	// Find the application directory
	        	String basepath = VaadinService.getCurrent()
	        	                  .getBaseDirectory().getAbsolutePath();

	        	// Image as a file resource
	        	String path = VaadinServlet.getCurrent().getServletContext().getRealPath("/content/");
	        	File file = new File(path, filename);
	        	fos = new FileOutputStream(file);
	            switch(selectType.getValue().toString()) {
	            case "IMG" :
	            	content_local.setType(ContentType.IMG);
	            	break;
	            case "VIDEO" :
	            	content_local.setType(ContentType.VIDEO);
	            	break;
	            case "AUDIO" :
	            	content_local.setType(ContentType.AUDIO);
	            	break;
	            default :
	            	content_local.setType(ContentType.NONE);
	            	break;
	            }
	            changeIcon();
	            content_local.setValue(file.toString());
	            //UPLOAD DONE - CHANGE ON SERVER SIDE!
	        } catch (final java.io.FileNotFoundException e) {
	            new Notification("Could not open file<br/>",
	                             e.getMessage(),
	                             Notification.Type.ERROR_MESSAGE)
	                .show(Page.getCurrent());
	            return null;
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return fos; 
	    }

	    public void uploadSucceeded(SucceededEvent event) {
	    	
	    }
	};

    Button b;
    Upload upload;
    public Content content_local;
    
    ThemeResource imageResource;
	Embedded contentEmbedded;
	ComboBox selectType;
	final BeanItemContainer<ContentType> container = new BeanItemContainer<ContentType>(ContentType.class);
    
    private static ThemeResource contentPlaceholder = new ThemeResource("images/GJAlogo.png");
    private static ThemeResource contentImg = new ThemeResource("images/image.png");
    private static ThemeResource contentVideo = new ThemeResource("images/video.png");
    private static ThemeResource contentAudio = new ThemeResource("images/audio.png");
    private static ThemeResource contentNotSet = new ThemeResource("images/cancel.png");
    
    private static String IMG = "IMG";
    private static String AUDIO = "AUDIO";
    private static String VIDEO = "VIDEO";
	
	ImageUploader receiver = new ImageUploader(); 
	
	protected void changeIcon() {
		switch(content_local.getType()) {
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
		contentEmbedded.setSource(imageResource);
	}

    public ContentCell(Content content, Button edit, GjaUI parentUI, GuiMain main, int iterator) {
        setSpacing(true);
        content_local = content;
        
        
		//imageResource = contentPlaceholder;
		contentEmbedded = new Embedded(null, null);
		changeIcon();
		contentEmbedded.setDescription(String.valueOf(iterator));
		contentEmbedded.addClickListener(new MouseEvents.ClickListener() {
			
			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
					//System.out.printf("CALLED FROM: %s\n", content.getDescription());
					if(!upload.isVisible()) {
					//main.request.setContent(main.notes.get(Integer.valueOf(contentEmbedded.getDescription())).getContent(),
					//		main.notes.get(Integer.valueOf(contentEmbedded.getDescription())));
					//System.out.printf("Now downloading file for note %d\n", Integer.valueOf(content.getDescription()));
					parentUI.addWindow(main.showContent = new ShowContent(main.notes.get(Integer.valueOf(contentEmbedded.getDescription())).getContent()));
					}
			}
		});
		addComponent(contentEmbedded);
		
		container.addItem(ContentType.IMG);
		container.addItem(ContentType.VIDEO);
		container.addItem(ContentType.AUDIO);
		
		selectType = new ComboBox("Select file type", container);
		selectType.setNullSelectionAllowed(false);
		selectType.setNewItemsAllowed(false);
		selectType.setValue(selectType.getItemIds().iterator().next());
        selectType.setImmediate(true);
        selectType.setVisible(false);
        addComponent(selectType);
        
        //addComponent(contentIMG);
        upload = new Upload("Multimedia upload", receiver);
        upload.setButtonCaption("New");
        upload.setVisible(false);
        upload.addSucceededListener(receiver);
        addComponent(upload);
        
        edit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				upload.setVisible(!upload.isVisible());
				selectType.setVisible(!selectType.isVisible());
				
			}
		});
    }

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("Clicked.");
	}
	
	
}
