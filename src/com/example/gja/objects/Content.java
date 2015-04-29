package com.example.gja.objects;

public class Content {
	public enum ContentType {
		IMG, VIDEO, AUDIO
	}
	
	private ContentType type;
	private String value;
	
	public Content(ContentType type, String value){
		this.value = value;
		this.type = type;
	}
	
	public String getType(){
		return this.type.toString();
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
