package controller;

import java.util.Map;
import model.*;
import view.WindowCreateUser;
import view.WindowModify;

public class LoginController {

	WorkerDAO dao = new ImplementsBD();

	public Map<String, Model> getModels(Worker worker) {
		return dao.getModels(worker);
	}
	public Map<String, Client> getClients(){
		return dao.getClients();
	}
	public void visualizarPantalla() {

		/*
		Worker work = new Worker("juan23","pass123",true,1);

		WindowModify ven = new WindowModify(this,work);*/
		CarDealership car = new CarDealership("AutoMadrid","Madrid",1);
		WindowCreateUser ven = new WindowCreateUser(car,this);
		ven.setVisible(true);
	}
	public boolean modifyModel(Model model) 
	{
		return dao.modifyModel(model);
	}
	public boolean insertClient(Client client) 
	{
		return dao.insertClient(client);
	}
	
}
