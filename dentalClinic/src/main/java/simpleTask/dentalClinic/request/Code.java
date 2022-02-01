package simpleTask.dentalClinic.request;

public class Code {

	private String number; // Povezujemo sa  atributom "code" iz User modela

	 public Code() {
		super();
	}

	public Code(String number) {
		super();
		this.number = number;

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	

}
