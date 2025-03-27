package principal;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Worker;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Worker c = new Worker(false, "Valencia Motors", "Valencia", 3);;
		LoginControlador cont = new LoginControlador();
		cont.openWindow(c);
	
	}
}
