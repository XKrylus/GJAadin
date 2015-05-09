package com.example.gja.objects;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Content  implements IsSerializable {
	public enum ContentType {
		IMG, VIDEO, AUDIO, TEXT, NONE
	}
	
	private ContentType type;
	private String value;
	private long id;
	
	public Content(ContentType type, String value){
		this.value = value;
		this.type = type;
	}
	
	public Content(){}
	
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
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public static ContentType encodeType(long l){
		if((long)l == (long)0){
			return ContentType.IMG;
		}else if((long)l == (long)1){
			return ContentType.VIDEO;
		}else if((long)l == (long)2){
			return ContentType.AUDIO;
		}else if((long)l == (long)3){
			return ContentType.TEXT;
		}else {
			return ContentType.NONE;
		}
	}
}
