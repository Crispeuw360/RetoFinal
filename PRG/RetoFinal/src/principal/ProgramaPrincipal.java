package principal;

import controlador.LoginController;
import modelo.CarDealership;
import modelo.Worker;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Worker c = new Worker(false, "Valencia Motors", "Valencia", 3);;
		LoginController cont = new LoginController();
		cont.openWindow(c);
	
	}
}
