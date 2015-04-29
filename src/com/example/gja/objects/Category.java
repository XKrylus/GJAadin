package com.example.gja.objects;

public class Category {
	private String name;
	private String description;
	
	public Category(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
}
