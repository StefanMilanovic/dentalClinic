package simpleTask.dentalClinic.request;

public class CancelAppointment {

	private String email;
	private String number;
	private String code;
	private String role;
	

	 public CancelAppointment() {
		super();
	}

	

	public CancelAppointment(String email, String number, String code, String role) {
		super();
		this.email = email;
		this.number = number;
		this.code = code;
		this.role = role;
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
