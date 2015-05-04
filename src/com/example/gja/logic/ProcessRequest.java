package com.example.gja.logic;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.gja.objects.Category;
import com.example.gja.objects.Content;
import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;

public class ProcessRequest {
	
	private static String SAMPLE_USER = "bena";
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
	public LinkedList<Note> notesDownloadAll() {
		return null;
	}
	
	//Download notes from category
	public LinkedList<Note> notesDownloadCategory(int CategoryId) {
		return null;
	}
	
	//Download notes with given tag
	public LinkedList<Note> notesDownloadTag(String tagId) {
		return null;
	}
	
	//Download note by ID - ??!! (TO JE NA CO?)
	//TODO!
	
	//Update all notes with local LinkedList, includes uploading new Notes (MOZNA (SNAD) NEBUDE POTREBA)
	public void updateNotes(LinkedList<Note> notes) {
		
	}
	
	//Upload single Note, by ID (SPIS NE)
	public void uploadNote(Note note) {
		
	}
	
	//Edit single Note, by ID (SPIS NE)
	public void editNote(int id) {
		
	}
	
	//Remove single Note, by ID (SPIS NE)
	public void removeNote(int id) {
		
	}
	
	//Return tag List
	public ArrayList<Tag> getTags() {
		return null;
	}
	
	//Add tag
	public void addTag(Tag tag) {
		
	}
	
	//Remove tag
	public void removeTag(Tag tag) {
		
	}
	
	//Return Category list
	public ArrayList<Category> getCategories() {
		return null;
	}
	
	//Add category
	public void addCategory(Category category) {
		
	}
	
	//Remove category
	public void removeCategory(Category category) {
		
	}
	
	//Set new content for Note
	public void setContent(Content content, Note note) {
		
	}

}
