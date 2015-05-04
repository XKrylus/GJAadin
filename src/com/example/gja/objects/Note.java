package com.example.gja.objects;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note {
	
	public enum state {
		DOT, EXCLAMATION, CHECK
	}
	//private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private String title;
	private Content content;	//Text of note, containing multimedial references
	private String user;
	private String description;
	private ArrayList<Content> attachments;
	private Date reminder;	//Date of reminder
	private Date created;	//Date of note creation
	private Date expire;	//Date of note creation
	private state state;//state of note (standard, warning, confirmed(but not disposed of))
	private ArrayList<Boolean> tags;	
	private int categories;
	private ArrayList<Comment> comments;

	/**
	 * General constructor for new note
	 * @param inputText			text of note, including multimedia
	 * @param inputReminder		reminder date of note
	 * @param inputExpire		expire date of note
	 * @param inputTag			selected tags for note
	 */
	public Note(String title, Content content, String desc, String user, Date inputReminder, Date inputExpire, /*PRYC*/state state, ArrayList<Boolean> tags,
				int categories, ArrayList<Comment> comments, ArrayList<Content> attachments) {
		this.title = title;
		this.content = content;
		this.reminder = inputReminder;
		this.created = new Date();
		this.expire = inputExpire;
		this.state = state.DOT;
		this.user = user;
		this.description = desc;
		/*if(!tags.equals(null)) {
			this.tags = tags;
		} else {
			this.tags = new ArrayList<Tag>();
		}*/
		this.tags = tags;
		
		this.categories = categories;
		
		if(!comments.equals(null)) {
			this.comments = comments;
		} else {
			this.comments = new ArrayList<Comment>();
		}
		
		if(!attachments.equals(null)) {
			this.attachments = attachments;
		} else {
			this.attachments = new ArrayList<Content>();
		}
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public Content getContent(){
		return this.content;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getDescription(){
		return this.description;
	}

	/**
	 * @return the text
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param text the text to set
	 */
	public void setTitle(String text) {
		this.title = text;
	}

	/**
	 * @return the reminder
	 */
	public Date getReminder() {
		return reminder;
	}

	/**
	 * @param reminder the reminder to set
	 */
	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the expire
	 */
	public Date getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

	/**
	 * @return the state
	 */
	public state getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(state state) {
		this.state = state;
	}

	/**
	 * @return the tag
	 */
	public ArrayList<Boolean> getTags() {
		return this.tags;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Boolean value, int tag) {
		this.tags.set(tag, value);
	}
	
	public void removeTag(Tag tag){
		int index = 0;
		if((index = this.tags.indexOf(tag)) != -1){
			this.tags.remove(index);
		}
	}
	
	public int getCategories(){
		return this.categories;
	}
	
	public void setCategory(int category) {
		this.categories = category;
	}
	
	public ArrayList<Comment> getComments(){
		return this.comments;
	}
	
	public void setComments(ArrayList<Comment> comments){
		if(!comments.equals(null)) {
			this.comments = new ArrayList<Comment>();
			this.comments = comments;
		} else {
			this.comments = new ArrayList<Comment>();
		}
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment){
		int index = 0;
		if((index = this.comments.indexOf(comment)) != -1){
			this.comments.remove(index);
		}
	}
	
	public ArrayList<Content> getAttachments(){
		return this.attachments;
	}
	
	public void addAttachment(Content content) {
		this.attachments.add(content);
	}
	
	public void removeAttachment(Content content){
		int index = 0;
		if((index = this.attachments.indexOf(content)) != -1){
			this.attachments.remove(index);
		}
	}

}