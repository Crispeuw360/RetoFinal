package model;

public class Client_ {

	private String dni;
	private String email;
	private String user_;
	private String password_;
	
	public Client_(String dni, String email, String user_, String password_) {
		super();
		this.dni = dni;
		this.email = email;
		this.user_ = user_;
		this.password_ = password_;
	}
	
	public Client_() {
		super();
		this.dni = "";
		this.email = "";
		this.user_ = "";
		this.password_ = "";
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_() {
		return user_;
	}

	public void setUser_(String user_) {
		this.user_ = user_;
	}

	public String getPassword_() {
		return password_;
	}

	public void setPassword_(String password_) {
		this.password_ = password_;
	}

	@Override
	public String toString() {
		return "Client_ [dni=" + dni + ", email=" + email + ", user_=" + user_ + ", password_=" + password_ + "]";
	}
	
	
	
	
}