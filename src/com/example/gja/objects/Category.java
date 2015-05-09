package com.example.gja.objects;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Category implements IsSerializable {

		private String name;
		private String description;
		private long id;
		
		public Category(String name, String description){
			this.name = name;
			this.description = description;
		}
		
		public Category(){}
		
		public String getDescription(){
			return this.description;
		}
		
		public String getName(){
			return this.name;
		}
		
		public void setDescription(String description){
			this.description = description;
		}
		
		public long getId(){
			return this.id;
		}
		
		public void setId(long id){
			this.id = id;
		}
	}
