package model;

public class CarDealership {

	private String name;
	private String location;
	private int id;

	public CarDealership(String name, String location, int id) {
		this.name = name;
		this.location = location;
		this.id = id;
	}

	public CarDealership() {
		this.name = "";
		this.location = "";
		this.id = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CarDealership [name=" + name + ", location=" + location + ", id=" + id + "]";
	}

}
