package com.example.gja.objects;

import com.google.gwt.user.client.rpc.IsSerializable;


public class Tag  implements IsSerializable {
	private String value;
	private long id;
		
	public Tag(String value){
		this.value = value;
	}
	
	public Tag(){}
	
	public String getValue(){
		return this.value;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}

