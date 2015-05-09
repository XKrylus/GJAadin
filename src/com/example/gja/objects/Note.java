package com.example.gja.objects;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Note  implements IsSerializable {
	
	private long id;
	private String title;
	private Content content;	//Text of note, containing multimedial references
	private String user;
	private String description;
	private Category category;
	private Date reminder;	//Date of reminder
	private Date created;	//Date of note creation
	private Date expire;	//Date of note creation
	private ArrayList<Tag> tags;	
	private ArrayList<Content> attachments;
	private ArrayList<Comment> comments;
	private boolean checked = false;

	public Note(String title, Content content, String desc, String user, Date inputReminder, Date inputExpire, ArrayList<Tag> tagsx,
				Category category, ArrayList<Comment> commentsx, ArrayList<Content> attachmentsx) {
		this.title = title;
		this.content = content;
		this.reminder = inputReminder;
		this.created = new Date();
		this.expire = inputExpire;
		this.user = user;
		this.description = desc;
		this.category = category;
		this.setChecked(false);
		
		this.tags = new ArrayList<Tag>();
		for(int i = 0; i < tagsx.size(); i++) {
			tags.add(tagsx.get(i));
		}
		
		this.comments = new ArrayList<Comment>();
		for(int i = 0; i < commentsx.size(); i++) {
			comments.add(commentsx.get(i));
		}
		

		this.attachments = new ArrayList<Content>();
		for(int i = 0; i < attachmentsx.size(); i++) {
			attachments.add(attachmentsx.get(i));
		}

	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
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
	 * @return the tag
	 */
	public ArrayList<Tag> getTags() {
		return this.tags;
	}

	/**
	 * @param tag the tag to set
	 */
	public void addTag(Tag tag) {
		boolean add = true;
		for(int i = 0; i < this.tags.size(); i++) {
			if(tag.getValue().equals(this.tags.get(i).getValue())) {
				add = false;
			}
		}
		if(add)this.tags.add(tag);
	}
	
	public void removeTag(Tag tag){
		int index = 0;
		boolean remove = false;
		for(int i = 0; i < this.tags.size(); i++) {
			if(tag.getValue().equals(this.tags.get(i).getValue())) {
				remove = true;
				index = i;
			}
		}
		if(remove)this.tags.remove(index);
	}
	
	public boolean containsTag(Tag tag) {
		for(int i = 0; i < this.tags.size(); i++) {
			if(tag.getValue().equals(this.tags.get(i).getValue())) {
				return true;
			}
		}
		return false;
	}
	
	public Category getCategories(){
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}