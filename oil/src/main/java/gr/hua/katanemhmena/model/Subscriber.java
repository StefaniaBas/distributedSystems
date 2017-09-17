package gr.hua.katanemhmena.model;

public class Subscriber {
	
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Subscriber [email="+email+"]";
	}
}
