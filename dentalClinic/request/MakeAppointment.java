package simpleTask.dentalClinic.request;

import java.util.Date;

public class MakeAppointment {

	private String email;
	private String number;
	private String code;
	private String role;
	private String time;
	private String duration;
	private Date date;
	
	 public MakeAppointment() {
		super();
	}

	public MakeAppointment(String email, String number, String code, String role, String time, String duration) {
		super();
		this.email = email;
		this.number = number;
		this.code = code;
		this.role = role;
		this.time = time;
		this.duration = duration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
