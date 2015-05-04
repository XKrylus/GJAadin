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

import com.example.gja.guiObjects.CommentCell;
import com.example.gja.guiObjects.ContentCell;
import com.example.gja.guiObjects.LabelRichCell;
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
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;

public class GuiMain  extends VerticalLayout{
	
	//Current user
	public String currentUser = "dev";
	
	//Class for server handling
	public ProcessRequest request = new ProcessRequest();
	
	//TopMenu Components
	Label topFlag = new Label("Flag");
	Label topName = new Label(currentUser);
	Label gap = new Label();
	Button addNote = new Button();
	Button logout = new Button();
	Button edit = new Button();
	MenuBar topMenuBar = new MenuBar();
	com.vaadin.ui.MenuBar.MenuItem topMenuBarOptions;
	MenuItem editTags;
	MenuItem editCategories;
	
	//List with Notes
	public LinkedList<Note> notes = new LinkedList<Note>();
	
	//Table
	TreeTable table = new TreeTable("Notes table");
	final Set<Object> selectedItemIds = new HashSet<Object>();
	//final Set<Object> selectedItemIds2 = new HashSet<Object>();
	boolean editable = false;
	boolean contentDownloadRemove = false;
	//GLobal size of tree on creation - because Vaadin TreeTable.size()...
	int tableSize = 0;
	
	ArrayList<Tag> tagsGlobal = new  ArrayList<Tag>();
	ArrayList<Category> categoriesGlobal = new ArrayList<Category>();
	
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
	
	private static int noCategory = -1;
	
	
	//GjaUI
	GjaUI parentUI;
	
	//ShowContent
	public ShowContent showContent;
	
	
	/**
	 * ??!
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	protected void loadTable(LinkedList<Note> notes) {
		table.removeAllItems();
		Object[] object = null;
		tableSize = 0;
		
		//Categories
		
		for(int i = 0; i < categoriesGlobal.size(); i++) {
			object = new Object[]{categoriesGlobal.get(i).getName(), null, null, null, null, null, null};
			table.addItem(object, i);
			
			table.getItem(i).getItemProperty(columnTitle).setReadOnly(true);
			table.getItem(i).getItemProperty(columnRemindOn).setReadOnly(true);
			table.getItem(i).getItemProperty(columnExpire).setReadOnly(true);
			table.setChildrenAllowed(i, true);
			tableSize++;
		}
		//System.out.printf("IDS OF CATEGORY are: %s\n", table.getItemIds().toString());
		
		//Actual notes
		for(int i = 0; i < notes.size(); i++) {
			//table.addItem(new Object[]{, , });
			
			String title = new String(notes.get(i).getTitle());
			
			ContentCell content;
			content = new ContentCell(notes.get(i).getContent(), edit, parentUI, this, i);
			
			//String description = new String(notes.get(i).getDescription());
			LabelRichCell description = new LabelRichCell(notes.get(i).getDescription(), edit);
			
			
			Date remindOn = notes.get(i).getReminder();
			Date inputExpire = notes.get(i).getExpire();
			
			ArrayList<Comment> hold = notes.get(i).getComments();
			CommentCell comment = new CommentCell(hold, edit, i);
			
			
			//MENU BAR PRO TAGY - SLOZITEC!!
			MenuBar tags = new MenuBar();
			com.vaadin.ui.MenuBar.MenuItem tagsOpen = tags.addItem("Tags", null);
			tagsOpen.setDescription(String.valueOf(i));
			
			
			MenuBar.Command checked = new MenuBar.Command() {
				
				@Override
				public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
					int index = Integer.valueOf(selectedItem.getParent().getDescription());
					if(selectedItem.isChecked()) {
						notes.get(index).getTags().set(selectedItem.getId() - 3, true);
					}
					else {
						notes.get(index).getTags().set(selectedItem.getId() - 3, false);
					}
				}
			};
			
			for(int j = 0; j < tagsGlobal.size(); j++) {
				tagsOpen.addItem(tagsGlobal.get(j).getValue(), checked).setCheckable(true);
				if(notes.get(i).getTags().size() < j + 1) {
					notes.get(i).getTags().add(false);
				}
				tagsOpen.getChildren().get(j).setChecked(notes.get(i).getTags().get(j));
			}			
			
			object = new Object[]{title, description, remindOn, inputExpire, comment, tags, content};
			
			table.addItem(object, i + categoriesGlobal.size());
			if(notes.get(i).getCategories() != noCategory) {
				table.setParent(i + categoriesGlobal.size(), notes.get(i).getCategories());
			}
			table.setChildrenAllowed(i + categoriesGlobal.size(), false);
			tableSize++;
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
		logout.setCaption("Logout");
		topMenu.addComponent(logout);
		topMenu.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);
		
		
		topMenu.addComponent(topMenuBar);
		topMenu.setComponentAlignment(topMenuBar, Alignment.MIDDLE_RIGHT);
		topMenuBarOptions = topMenuBar.addItem("Options", null, null);
		topMenuBarOptions.addItem("Search", null, null);
		topMenuBarOptions.addItem("Sync", null, null);
		editTags = topMenuBarOptions.addItem("Edit Tags", null,null);
		editCategories = topMenuBarOptions.addItem("Edit Categories", null, null);
		
		return topMenu;
	}
	
	/*public Note(String title, Content content, String desc, String user, Date inputReminder, Date inputExpire, state state, ArrayList<Tag> tags,
	ArrayList<Category> categories, ArrayList<Comment> comments, ArrayList<Content> attachments);*/
	
	protected Table tableInit(Table table) {
		
		//Table init
		table.setWidth("100%");
		//table.setHeight("100%");
		//table.setEditable(true);
		table.addStyleName("components-inside");
		table.setSelectable(false);
		
		IndexedContainer ic = new IndexedContainer();
		
		table.addContainerProperty(columnTitle,         String.class,   null);		
		table.addContainerProperty(columnDescription,   LabelRichCell.class,   null);
		table.addContainerProperty(columnRemindOn,      Date.class,    	null);
		table.addContainerProperty(columnExpire,    	Date.class,    	null);
		table.addContainerProperty(columnComment,       CommentCell.class, null);
		table.addContainerProperty(columnTag,           MenuBar.class, 	null);
		table.addContainerProperty(columnContent,       ContentCell.class, null);
		
		table.addGeneratedColumn(columnRemove, new Table.ColumnGenerator() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				//boolean selected = selectedItemIds.contains(itemId);
		        /* When the check box value changes, add/remove the itemId from the selectedItemIds set */
		        final CheckBox cb = new CheckBox("", false);
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
		
		table.setColumnExpandRatio(columnTitle, 4);
		table.setColumnExpandRatio(columnDescription, 5);
		table.setColumnExpandRatio(columnRemindOn, 4);
		table.setColumnExpandRatio(columnExpire, 4);
		table.setColumnExpandRatio(columnComment, 4);
		table.setColumnExpandRatio(columnTag, 2);
		table.setColumnExpandRatio(columnContent, 5);
		table.setColumnExpandRatio(columnRemove, 2);
		
		return table;
	}
	
	/**
	 * Updates LinkedList Notes after editing Table
	 * Size of notes will not change during edit
	 */
	protected void editedNotes() {
		
		LabelRichCell x;
		CommentCell commentCellx = null;
		
		for(int i = 0; i < tableSize - categoriesGlobal.size(); i++) {
			Item item = table.getItem(i + categoriesGlobal.size());
			//Title
			notes.get(i).setTitle(String.valueOf(item.getItemProperty(columnTitle).getValue()));
			//Description
			x = (LabelRichCell) item.getItemProperty(columnDescription).getValue();
			notes.get(i).setDescription(x.getRichText().getValue());
			//Date - reminds on
			notes.get(i).setReminder((Date) item.getItemProperty(columnRemindOn).getValue());
			//Date - expires on
			notes.get(i).setExpire((Date) item.getItemProperty(columnExpire).getValue());
			//Content - TODO
			//Attachments - TODO
			//Comments
			commentCellx = (CommentCell) item.getItemProperty(columnComment).getValue();
			notes.get(i).setComments(commentCellx.getCommentList());
		}
		loadTable(notes);
		// SERVER - Notes edited in various ways - update on server
        request.updateNotes(notes);
	}

	public GuiMain(GjaUI ui) {
		
		parentUI = ui;
		
		//this.addComponent(new Label("Hello Bena!"));
		//this.setSizeFull();
		
		//TopMenu init
		HorizontalLayout topMenu = new HorizontalLayout();
		this.addComponent(topMenu);
		topMenu.setWidth("100%");
		//topMenu.setHeight("100%");
		
		//Init Top Menu (add components)
		topMenuInit(topMenu);
		
		//Notes
		
		VerticalLayout notesSpace = new VerticalLayout();
		this.addComponent(notesSpace);
		//notesSpace.setSizeFull();
		notesSpace.setWidth("100%");
		this.setExpandRatio(topMenu, 0.08f);
		this.setExpandRatio(notesSpace, 0.92f);
		
		//Buttons space
		HorizontalLayout notesSpaceButtons = new HorizontalLayout();
		notesSpace.setSpacing(true);
		notesSpace.addComponent(notesSpaceButtons);
		
		//Init Table
		Panel panelTable = new Panel();
		notesSpace.addComponent(panelTable);
		tableInit(table);
		panelTable.setContent(table);
		
		notesSpaceButtons.addComponent(new Button("Delete", new Button.ClickListener() {

			@Override
		      public void buttonClick(Button.ClickEvent event) {
				ArrayList<Integer> index = new ArrayList();
		        for (Object itemId : selectedItemIds) {
		          index.add((int)(itemId) - categoriesGlobal.size());
		          }
		        int j = 0;
		        for(int i = 0; i < index.size(); i++) {
		        	notes.remove(index.get(i) + j);
		        	j--;
		        }
		        loadTable(notes);
		        // SERVER - Notes removed - update on server
		        request.updateNotes(notes);
		      }
		    }));
		notesSpaceButtons.addComponent(edit = new Button("Edit", new Button.ClickListener() {

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
		
		for(int i = 0; i < 5; i++) {
			categoriesGlobal.add(new Category("Kategorie c. " + i, "Vychozi kategorie"));
		}
		
		//Initial notes
		String[] title = {"Nakup", "Prodej", "Vydej", "Binej"};
		Content[] content = {new Content(ContentType.IMG, "images/logo.png"), new Content(ContentType.AUDIO, "audio/record.mp3"), 
				new Content(ContentType.VIDEO, "video/video.mp4"), new Content(ContentType.NONE, null)};
		String[] description = {"Mleko 4 rohliky", "Vrtacka Bena", "4 lahve praveho Hamsika a jedna Zelena, hodne skorice a hlavne, hlavne, tresnovice", "Navstivit Hryzacke Kresice"};
		Date[] inputReminder = {new Date(), new Date(), new Date(), new Date()};
		Date[] inputExpire = {new Date(), new Date(), new Date(), new Date()};
		state[] state = {null, null, null, null};
		ArrayList<Boolean> setTags = new  ArrayList<Boolean>();
		for(int i = 0; i != tagsGlobal.size(); i++) {
			Boolean tag;
			if(i < 2)tag = true;
			else tag = false;
			setTags.add(tag);
		}
		int[] categories = {0,1,2,3,4};
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for(int i = 0; i < 4; i++) {
			comments.add(new Comment(this.currentUser, i + ": Chci komentovat tuto vec, ale jak...?"));
		}
		ArrayList<Content> attachments = new ArrayList<Content>();
		
		for(int i = 0; i < title.length; i++) {
			notes.add(new Note(title[i], content[i], description[i], currentUser, inputReminder[i], inputExpire[i], state[i], setTags, categories[i], comments, attachments));
		}
		
		//Load initial notes to table
		loadTable(notes);
		
		notesSpace.setExpandRatio(panelTable, 0.8f);
		notesSpace.setExpandRatio(notesSpaceButtons, 0.2f);
	}
}
