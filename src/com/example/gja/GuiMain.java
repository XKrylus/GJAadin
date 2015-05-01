package com.example.gja;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.remote.server.handler.GetWindowPosition;

import com.example.gja.logic.ProcessRequest;
import com.example.gja.objects.Category;
import com.example.gja.objects.Comment;
import com.example.gja.objects.Content;
import com.example.gja.objects.Content.ContentType;
import com.example.gja.objects.Note;
import com.example.gja.objects.Note.state;
import com.example.gja.objects.Tag;
import com.ibm.icu.text.DateFormat;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;

public class GuiMain  extends VerticalLayout{
	
	//Current user
	public String currentUser = "dev";
	
	//Class for server handling
	ProcessRequest request = new ProcessRequest();
	
	//TopMenu Components
	Label topFlag = new Label("Flag");
	Label topName = new Label(currentUser);
	Label gap = new Label();
	Button addNote = new Button();
	MenuBar topMenuBar = new MenuBar();
	com.vaadin.ui.MenuBar.MenuItem topMenuBarOptions;
	MenuItem editTags;
	
	//List with Notes
	LinkedList<Note> notes = new LinkedList<Note>();
	
	//Table
	Table table = new Table("Notes table");
	final Set<Object> selectedItemIds = new HashSet<Object>();
	//final Set<Object> selectedItemIds2 = new HashSet<Object>();
	boolean editable = false;
	boolean contentDownloadRemove = false;
	ArrayList<Tag> tagsGlobal = new  ArrayList<Tag>();
	
	//Table columns
	private static String columnTitle = "Title";
	private static String columnDescription = "Description";
	private static String columnRemindOn = "Remind On";
	private static String columnExpire = "Input Expire";
	private static String columnComment = "Comment";
	private static String columnTag = "Tag";
	private static String columnContent = "Content";
	private static String columnDownloadEditContent = "Show Content";
	private static String columnRemove = "Remove";
	
	
	ThemeResource contentPlaceholder = new ThemeResource("images/GJAlogo.png");
	ThemeResource contentImg = new ThemeResource("images/image.png");
	ThemeResource contentVideo = new ThemeResource("images/video.png");
	ThemeResource contentAudio = new ThemeResource("images/audio.png");
	ThemeResource contentNotSet = new ThemeResource("images/notSet.png");
	
	
	//GjaUI
	GjaUI parentUI;
	
	//ShowContent
	ShowContent showContent;
	
	
	/**
	 * ??!
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	protected void loadTable(LinkedList<Note> notes) {
		table.removeAllItems();
		for(int i = 0; i < notes.size(); i++) {
			//table.addItem(new Object[]{, , });
			
			String title = new String(notes.get(i).getTitle());
			
			ThemeResource imageResource;
			Embedded content;
			switch(notes.get(i).getContent().getType()) {
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
			content = new Embedded("Content", imageResource);
			String description = new String(notes.get(i).getDescription());
			
			Date remindOn = notes.get(i).getReminder();
			Date inputExpire = notes.get(i).getExpire();
			
			TextArea comment = new TextArea();
			
			
			//MENU BAR PRO TAGY - SLOZITEC!!
			MenuBar tags = new MenuBar();
			MenuBar.Command checked = new MenuBar.Command() {
				
				@Override
				public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
					int index = Integer.valueOf(selectedItem.getParent().getText().substring(selectedItem.getParent().getText().length() - 1));
					if(selectedItem.isChecked()) {
						notes.get(index).getTags().set(selectedItem.getId() - 3, true);
					}
					else {
						notes.get(index).getTags().set(selectedItem.getId() - 3, false);
					}
				}
			};
			
			com.vaadin.ui.MenuBar.MenuItem tagsOpen = tags.addItem("Tags " + i, null);
			for(int j = 0; j < tagsGlobal.size(); j++) {
				tagsOpen.addItem(tagsGlobal.get(j).getValue(), checked).setCheckable(true);
				if(notes.get(i).getTags().size() < j + 1) {
					notes.get(i).getTags().add(false);
				}
				tagsOpen.getChildren().get(j).setChecked(notes.get(i).getTags().get(j));
			}			
			
			table.addItem(new Object[]{title, description, remindOn, inputExpire, comment, tags, content}, i);
		}
	}
	
	protected HorizontalLayout topMenuInit(HorizontalLayout topMenu) {
		//Top Menu Components init
		topMenu.addComponent(topFlag);
		topMenu.addComponent(topName);
		gap.setWidth("100%");
		topMenu.addComponent(gap);
		addNote.setCaption("+");
		topMenu.addComponent(addNote);
		topMenu.setComponentAlignment(addNote, Alignment.MIDDLE_RIGHT);
		
		
		topMenu.addComponent(topMenuBar);
		topMenu.setComponentAlignment(topMenuBar, Alignment.MIDDLE_RIGHT);
		topMenuBarOptions = topMenuBar.addItem("Options", null, null);
		topMenuBarOptions.addItem("Search", null, null);
		topMenuBarOptions.addItem("Sync", null, null);
		editTags = topMenuBarOptions.addItem("Edit Tags", null,null);
		topMenuBarOptions.addItem("Confirm", null, null);
		
		return topMenu;
	}
	
	/*public Note(String title, Content content, String desc, String user, Date inputReminder, Date inputExpire, state state, ArrayList<Tag> tags,
	ArrayList<Category> categories, ArrayList<Comment> comments, ArrayList<Content> attachments);*/
	
	protected Table tableInit(Table table) {
		
		//Table init
		table.setWidth("100%");
		table.setHeight("100%");
		table.addStyleName("components-inside");
		table.setSelectable(true);
		
		IndexedContainer ic = new IndexedContainer();
		
		table.addContainerProperty(columnTitle,         String.class,   null);		
		table.addContainerProperty(columnDescription,   String.class,   null);
		table.addContainerProperty(columnRemindOn,      Date.class,    	null);
		table.addContainerProperty(columnExpire,    	Date.class,    	null);
		table.addContainerProperty(columnComment,       TextArea.class, null);
		table.addContainerProperty(columnTag,           MenuBar.class, 	null);
		table.addContainerProperty(columnContent,       Embedded.class, null);
		
		table.addGeneratedColumn(columnDownloadEditContent, new Table.ColumnGenerator() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
		        /* When the check box value changes, add/remove the itemId from the selectedItemIds set */
		        final Button btn = new Button("Show Content");
		        btn.addClickListener(new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						if(editable) {
							//System.out.printf("ITEM ID: %s COLUMN ID: %s\n", itemId, columnId);
						}
						else {
							request.downloadContent(notes.get((int) itemId).getContent());
							System.out.printf("Now downloading file for note %d\n", (int) itemId);
							parentUI.addWindow(showContent = new ShowContent(notes.get((int) itemId).getContent()));
						}
					}
		          });
		          return btn;
		        }
		});
		
		table.addGeneratedColumn(columnRemove, new Table.ColumnGenerator() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				boolean selected = selectedItemIds.contains(itemId);
		        /* When the check box value changes, add/remove the itemId from the selectedItemIds set */
		        final CheckBox cb = new CheckBox("", selected);
		        cb.addListener(new Property.ValueChangeListener() {
		            @Override
		            public void valueChange(Property.ValueChangeEvent event) {
		              if(selectedItemIds.contains(itemId)){
		                selectedItemIds.remove(itemId);
		              } else {
		                selectedItemIds.add(itemId);
		              }
		            }
		          });
		          return cb;
		        }
		});
		
		
		
		return table;
	}
	
	/**
	 * Updates LinkedList Notes after editing Table
	 * Size of notes will not change during edit
	 */
	protected void editedNotes() {
		for(int i = 0; i < table.size(); i++) {
			Item item = table.getItem(i);
			//Title
			notes.get(i).setTitle(String.valueOf(item.getItemProperty(columnTitle).getValue()));
			//Description
			notes.get(i).setDescription(String.valueOf(item.getItemProperty(columnDescription).getValue()));
			//Date - reminds on
			notes.get(i).setReminder((Date) item.getItemProperty(columnRemindOn).getValue());
			//Date - expires on
			notes.get(i).setExpire((Date) item.getItemProperty(columnExpire).getValue());
			//Content - TODO
			//Attachments - TODO
			//Comments - TODO (??)
		}
		loadTable(notes);
	}

	public GuiMain(GjaUI ui) {
		
		parentUI = ui;
		
		//this.addComponent(new Label("Hello Bena!"));
		this.setSizeFull();
		
		//TopMenu init
		HorizontalLayout topMenu = new HorizontalLayout();
		this.addComponent(topMenu);
		topMenu.setWidth("100%");
		topMenu.setHeight("100%");
		
		//Init Top Menu (add components)
		topMenuInit(topMenu);
		
		//Notes
		
		VerticalLayout notesSpace = new VerticalLayout();
		this.addComponent(notesSpace);
		notesSpace.setSizeFull();
		notesSpace.setWidth("100%");
		this.setExpandRatio(topMenu, 0.05f);
		this.setExpandRatio(notesSpace, 0.95f);
		
		
		//Init Table
		tableInit(table);
		notesSpace.addComponent(table);
		
		VerticalLayout notesSpaceButtons = new VerticalLayout();
		notesSpace.addComponent(notesSpaceButtons);
		notesSpaceButtons.addComponent(new Button("Delete", new Button.ClickListener() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		      public void buttonClick(Button.ClickEvent event) {
		        for (Object itemId : selectedItemIds) {
		          notes.remove((int)itemId);
		          }
		        loadTable(notes);
		      }
		    }));
		notesSpaceButtons.addComponent(new Button("Edit", new Button.ClickListener() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		      public void buttonClick(Button.ClickEvent event) {
				editable = !editable;
				table.setEditable(editable);
				//Update notes after leaving edit mode
				if(!editable) {
					editedNotes();
				}
				//Disable buttons while in edit mode
				notesSpaceButtons.getComponent(0).setEnabled(!editable);
				addNote.setEnabled(!editable);
				topMenuBar.setEnabled(!editable);
		      }
		    }));
		
		//Initial tags
		for(int i = 0; i != 5; i++) {
			Tag tag = new Tag ("Bena c." + String.valueOf(i));
			tagsGlobal.add(tag);
		}
		
		//Initial notes
		String[] title = {"Nakup", "Prodej", "Vydej"};
		Content[] content = {new Content(ContentType.IMG, "images/logo.png"), new Content(ContentType.AUDIO, "audio/record.mp3"), new Content(ContentType.VIDEO, "video/video.mp4")};
		String[] description = {"Mleko 4 rohliky", "Vrtacka Bena", "4 lahve praveho Hamsika a jedna Zelena"};
		Date[] inputReminder = {new Date(), new Date(), new Date()};
		Date[] inputExpire = {new Date(), new Date(), new Date()};
		state[] state = {null, null, null};
		ArrayList<Boolean> setTags = new  ArrayList<Boolean>();
		for(int i = 0; i != tagsGlobal.size(); i++) {
			Boolean tag;
			if(i < 2)tag = true;
			else tag = false;
			setTags.add(tag);
		}
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		ArrayList<Content> attachments = new ArrayList<Content>();
		
		for(int i = 0; i < title.length; i++) {
			notes.add(new Note(title[i], content[i], description[i], currentUser, inputReminder[i], inputExpire[i], state[i], setTags, categories, comments, attachments));
		}
		
		//Load initial notes to table
		loadTable(notes);
		
		notesSpace.setExpandRatio(table, 0.8f);
		notesSpace.setExpandRatio(notesSpaceButtons, 0.2f);
	}
}
