package com.example.gja.objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class defines user credentials to be stored and referenced
 * @author Honza
 *
 */
public class User {
	private int id;			//user id
	private String passwd;	//user password, encrypted with MD5
	private String email;	//user email
	
	private static MessageDigest md;
	
	
	/**
	 * Generates ID for note, UUID style generation
	 * @return	generated ID for user
	 */
	private int generateID() {
		return Integer.valueOf(String.valueOf(UUID.randomUUID()));
	}
	
	/**
	 * Encrypts user password using MD5 standard
	 * @param passwd	password given by user
	 * @return			encrypted password, OR null if error occurs
	 */
	private static String encryptPasswd(String passwd){
		
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = passwd.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
	    }
	        return null;


	   }
	
	/**
	 * General constructor for new User
	 * @param inputEmail	email of user
	 * @param inputPasswd	password of user
	 */
	public User(String inputEmail, String inputPasswd) {
		id = generateID();
		passwd = encryptPasswd(inputPasswd);
		email = inputEmail;
		}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
