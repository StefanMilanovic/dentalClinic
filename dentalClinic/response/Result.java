package simpleTask.dentalClinic.response;

public class Result {

	private String text;

	 public Result() {
		super();
	}

	public Result(String text, String number, String code, String role) {
		super();
		this.text = text;
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
    public String toString() {
        return "Result{" +
                "text='" + text + '\'' +	                             
                '}';
    }
}
