package com.example.gja.objects;

//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Class defines note to be used in application
 * @author Honza
 *
 */
public class Note {
	
	private enum state {
		DOT, EXCLAMATION, CHECK
	}
	//private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private int id;			//ID of note
	private String text;	//Text of note, containing multimedial references
	
	private Date reminder;	//Date of reminder
	private Date created;	//Date of note creation
	private Date expire;	//Date of expiration
	private state state;//state of note (standard, warning, confirmed(but not disposed of))
	private boolean[] tag;	//is true for tags this note belongs to
	
	
	/**
	 * Generates ID for note, UUID style generation
	 * @return	generated ID for note
	 */
	private int generateID() {
		return Integer.valueOf(String.valueOf(UUID.randomUUID()));
	}

	/**
	 * General constructor for new note
	 * @param inputText			text of note, including multimedia
	 * @param inputReminder		reminder date of note
	 * @param inputExpire		expire date of note
	 * @param inputTag			selected tags for note
	 */
	public Note(String inputText, Date inputReminder, Date inputExpire, boolean[] inputTag) {
		id = generateID();
		text = inputText;
		reminder = inputReminder;
		created = new Date();
		expire = inputExpire;
		state = state.DOT;
		tag = inputTag;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the reminder
	 */
	public Date getReminder() {
		return reminder;
	}

	/**
	 * @param reminder the reminder to set
	 */
	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the expire
	 */
	public Date getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

	/**
	 * @return the state
	 */
	public state getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(state state) {
		this.state = state;
	}

	/**
	 * @return the tag
	 */
	public boolean[] getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(boolean[] tag) {
		this.tag = tag;
	}

}
