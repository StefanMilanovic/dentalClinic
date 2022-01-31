package simpleTask.dentalClinic.model;

public class User {
	private long id;
	private String email;
	private String number;
	private String code;
	private String role;
	
	 public User() {
		super();
	}

	public User(long id, String email, String number, String code, String role) {
		super();
		this.id = id;
		this.email = email;
		this.number = number;
		this.code = code;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
