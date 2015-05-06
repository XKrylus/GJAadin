package com.example.gja.logic;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.gja.objects.Category;
import com.example.gja.objects.Content;
import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;

public class ProcessRequest {
	
	private static String SAMPLE_USER = "Honza";
	private static String SAMPLE_PASSWORD = "bewna";
	
	public ProcessRequest() {
		
	}
	
	//User Managment
	//Login User
	public boolean userLogin(String userName, String password) {
		if(userName.equals(SAMPLE_USER) && password.equals(SAMPLE_PASSWORD)) return true;
		else return false;
	}
	//Register User
	public boolean userRegister(String userName, String password) {
		if(userName.equals(SAMPLE_USER)) return false;
		else return true;
	}
	//Logout User
	public void userLogout(String userName) {
		
	}
	
	//Note Managment
	
	//Download all notes
	public LinkedList<Note> notesDownloadAll(String user) {
		return null;
	}
	
	//Download notes with fulltext
	public LinkedList<Note> notesDownloadFulltext(String user, String fulltext) {
		return null;
	}
	
	//Download notes from category
	public LinkedList<Note> notesDownloadCategory(String user, Category category) {
		return null;
	}
	
	//Download notes with given tag
	public LinkedList<Note> notesDownloadTag(String user, ArrayList<Tag> tags) {
		return null;
	}
	
	//Download note by ID - ??!! (TO JE NA CO?)
	//TODO!
	
	//Upload single Note
	public void uploadNote(String user, Note note) {
		
	}
	
	//Edit single Note
	public void editNote(String user, int id, Note note) {
		
	}
	
	//Remove single Note
	public void removeNote(String user, int id) {
		
	}
	
	//Return tag List
	public ArrayList<Tag> getTags(String user) {
		return null;
	}
	
	//Add tag
	public void addTag(String user, Tag tag) {
		
	}
	
	//Remove tag
	public void removeTag(String user, Tag tag) {
		
	}
	
	//Return Category list
	public ArrayList<Category> getCategories(String user) {
		return null;
	}
	
	//Add category
	public void addCategory(String user, Category category) {
		
	}
	
	//Remove category
	public void removeCategory(String user, Category category) {
		
	}
	
	//Set new content for Note
	public void setContent(String user, Content content, Note note) {
		
	}

}
