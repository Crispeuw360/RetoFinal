package model;

public class Worker {

	private boolean admin;
	private String user;
	private String password;
	private int id_car_dealer;

	public Worker(boolean admin, String user, String password, int id_car_dealer) {
		this.admin = admin;
		this.user = user;
		this.password = password;
		this.id_car_dealer = id_car_dealer;
	}

	public Worker() {
		this.admin = false;
		this.user = "";
		this.password = "";
		this.id_car_dealer = 0;
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

	public int getId_car_dealer() {
		return id_car_dealer;
	}

	public void setId_car_dealer(int id_car_dealer) {
		this.id_car_dealer = id_car_dealer;
	}

	@Override
	public String toString() {
		return "Worker [Admin=" + admin + ", user=" + user + ", password=" + password + "]";
	}

}
