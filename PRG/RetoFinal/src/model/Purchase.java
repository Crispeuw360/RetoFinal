package model;

import java.time.LocalDate;

public class Purchase {

	private String dni;
	private String name_model;
	private int id_car_dealer;
	private LocalDate date_purchase;
	private int quantity;

	public Purchase(String dni, String name_model, int id_car_dealer, LocalDate date_purchase, int quantity) {
		super();
		this.dni = dni;
		this.name_model = name_model;
		this.id_car_dealer = id_car_dealer;
		this.date_purchase = date_purchase;
		this.quantity = quantity;
	}

	public Purchase() {
		super();
		this.dni = "";
		this.name_model = "";
		this.id_car_dealer = 0;
		this.date_purchase = null;
		this.quantity = 0;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName_model() {
		return name_model;
	}

	public void setName_model(String name_model) {
		this.name_model = name_model;
	}

	public int getId_car_dealer() {
		return id_car_dealer;
	}

	public void setId_car_dealer(int id_car_dealer) {
		this.id_car_dealer = id_car_dealer;
	}

	public LocalDate getDate_purchase() {
		return date_purchase;
	}

	public void setDate_purchase(LocalDate date_purchase) {
		this.date_purchase = date_purchase;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Purchase [dni=" + dni + ", name_model=" + name_model + ", id_car_dealer=" + id_car_dealer
				+ ", date_purchase=" + date_purchase + ", quantity=" + quantity + "]";
	}

}
