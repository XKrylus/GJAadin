package com.example.gja.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.example.gja.comm.Comm;
import com.example.gja.objects.Category;
import com.example.gja.objects.Content;
import com.example.gja.objects.Comment;
import com.example.gja.objects.Note;
import com.example.gja.objects.Tag;
import com.google.gwt.core.client.GWT;
import com.vaadin.ui.Window;

public class ProcessRequest {
	
	private static String SAMPLE_USER = "Honza";
	private static String SAMPLE_PASSWORD = "bewna";
	
	//private Comm client = GWT.create(Comm.class);
	
	public ProcessRequest() {
		
	}
	
	//User Managment
	//Login User
	public boolean userLogin(String userName, String password) {
		
		/*client.userLogin(userName, password, new MethodCallback<String>() {
			
			@Override
			public void onSuccess(Method method, String response) {
				System.out.println(response);
				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				System.out.println("Error: "+exception);
				
			}
		});*/
		
		if(userName.equals(SAMPLE_USER) && password.equals(SAMPLE_PASSWORD)) return true;
		else return false;
	}
	//Register User
	public long userRegister(String userName, String password) {
		return 0;
	} //vlozi noveho uzivatela do datastore
	
	//Note Managment
	
	//vrati vsetky poznamky daneho uzivatela
	public LinkedList<Note> notesDownloadAll(String user) {
		return null;
	}
	
	//vrati vsetky poznamky daneho uzivatela podla zadaneho fulltext vyhladania
	public LinkedList<Note> notesDownloadFulltext(String user, String fulltext) {
		return null;
	}
	
	//vrati vsetky poznamky daneho uzivatela podla v zadanej kategorie
	public LinkedList<Note> notesDownloadCategory(String user, long CategoryId) {
		return null;
	} 
	
	//vrati vsetky poznamky daneho uzivatela so zadanym tagom
	public LinkedList<Note> notesDownloadTag(String user, ArrayList<Tag> tags) {
		return null;
	}
	
	//vlozi poznamku do datastore 
	public long uploadNote(String user, Note note) {
		return 0;
	}
	
	//odstrani poznamku z datastore
	public void removeNote(long id) {
	} 
	
	//vrati vsetky tagy daneho uzivatela
	public ArrayList<Tag> getTags(String user) {
		return null;
	}
	
	//prida tag do datastore
	public long addTag(String user, Tag tag) {
		return 0;
	}
	
	//odstrani tag do datastore
	public void removeTag(String user, Tag tag) {
	}
	
	//vrati vsetky kategorie daneho uzivatela
	public ArrayList<Category> getCategories(String user) {
		return null;
	}
	
	//prida kategoriu do datastore
	public long addCategory(String user, Category category) {
		return 0;
	}
	
	//odstrani kategoriu z datastore
	public void removeCategory(String user, Category category) {
	}
	
	//nastavi obsah poznamky v datastore
	public long setContent(String user, Content content, Note note) {
		return 0;
	}
	
	//upravi nadpis poznamky v datastore
	public void changeTitle(long id, String title) {
	}
	
	//upravi popis poznamky v datastore
	public void changeDescription(long id, String description) {
	}
	
	//upravi datum pripomenutia poznamky v datastore
	public void changeReminder(long id, Date date) {
	}
	
	//upravi datum vytvorenia poznamky v datastore
	public void changeCreated(long id, Date date) {
	}
	
	//upravi datum vyprsania poznamky v datastore
	public void changeExpire(long id, Date date) {
	}
	
	//odstrani obsah poznamky z datastore - NOT USED!
	public void removeContent(long id) {
	} 
	
	//upravi kategoriu poznamky v datastore
	public void changeCategory(long id, long categoryId) {
	} 
	
	//odstrani komentar k poznamke v datastore
	public void removeComment(long id) {
	} 
	
	//prida komentar k poznamke v datastore
	public long addComment(String user, long noteId, Comment comment) {
		return 0;
	} 
	
	//odstrani tag k poznamke v datastore
	public void removeTagFromNote(long noteId, long tagId) {
	} 
	
	//prida tag k poznamke v datastore
	public void addTagToNote(String user, long noteId, long tagId) {
	} 
	
	//prida prilohu k poznamke v datastore - NOT IMPLEMENTED!
	public long addAttachment(String user, long noteId, Content attachment) {
		return 0;
	}
	
	//odstrani prilohu k poznamke v datastore - NOT IMPLEMENTED!
	public void removeAttachment(long id) {
	}
	
}
