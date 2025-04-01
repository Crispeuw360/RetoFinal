package model;

public class Model {

	private String name_model;
	private String mark;
	private int stock;
	private double price;
	private int id_car_dealer;

	public Model(String name_model, String mark, int stock, double price, int id_car_dealer) {
		super();
		this.name_model = name_model;
		this.mark = mark;
		this.stock = stock;
		this.price = price;
		this.id_car_dealer = id_car_dealer;
	}

	public Model() {
		super();
		this.name_model = "";
		this.mark = "";
		this.stock = 0;
		this.price = 0;
		this.id_car_dealer = 0;
	}

	public String getName_model() {
		return name_model;
	}

	public void setName_model(String name_model) {
		this.name_model = name_model;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId_car_dealer() {
		return id_car_dealer;
	}

	public void setId_car_dealer(int id_car_dealer) {
		this.id_car_dealer = id_car_dealer;
	}

	@Override
	public String toString() {
		return "Model [name_model=" + name_model + ", mark=" + mark + ", stock=" + stock + ", price=" + price
				+ ", id_car_dealer=" + id_car_dealer + "]";
	}

}
