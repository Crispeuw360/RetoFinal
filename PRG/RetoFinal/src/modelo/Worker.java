package modelo;

public class Worker {

	private boolean admin;
	private String user;
	private String password;
	
	public Worker(boolean Admin, String user, String password) 
	{
		this.admin = Admin;
		this.user = user;
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Worker [Admin=" + admin + ", user=" + user + ", password=" + password + "]";
	}
	
	
}
