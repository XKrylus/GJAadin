package com.example.gja.objects;

import java.util.Date;

public class Comment {
	private String author;
	private Date created;
	private String value;
	
	public Comment(String author, String value){
		this.author = author;
		this.created = new Date();
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public Date getCreated(){
		return this.created;
	}
}
