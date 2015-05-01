package com.example.gja.objects;


public class Tag {
	private String value;
	//private int id;
		
	public Tag(String value){
		this.value = value;
		//this.id = Integer.valueOf(String.valueOf(UUID.randomUUID()));
	}
	
	public String getValue(){
		return this.value;
	}
	
	/*public int getId(){
		return this.id;
	}*/
}
