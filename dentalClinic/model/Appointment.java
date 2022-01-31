package simpleTask.dentalClinic.model;

import java.util.Date;

public class Appointment {
	
	private long id;
	private User user;
	private Date date;
	private String duration;
	private String type;
	private boolean deleted;
	
	 public Appointment() {
		super();
	}
	 

	public Appointment(long id, User user, Date date, String duration, String type, boolean deleted) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.duration = duration;
		this.type = type;
		this.deleted = deleted;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	

}
