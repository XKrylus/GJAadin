package com.example.gja.objects;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Comment  implements IsSerializable {

		private long id;
		private String author;
		private Date created;
		private String value;
		
		public Comment(String author, String value, Date date){
			this.author = author;
			this.created = date;
			this.value = value;
		}
		
		public Comment(){}
		
		public String getValue(){
			return this.value;
		}
		
		public String getAuthor(){
			return this.author;
		}
		
		public Date getCreated(){
			return this.created;
		}
		
		public long getId(){
			return this.id;
		}
		
		public void setId(long id){
			this.id = id;
		}
	}
