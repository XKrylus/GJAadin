package com.example.gja.objects;

public class Content {
	public enum ContentType {
		IMG, VIDEO, AUDIO, NONE
	}
	
	private ContentType type;
	private String value;
	
	public Content(ContentType type, String value){
		this.value = value;
		this.type = type;
	}
	
	public ContentType getType(){
		return this.type;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public void setType(ContentType type){
		this.type = type;
	}
}
