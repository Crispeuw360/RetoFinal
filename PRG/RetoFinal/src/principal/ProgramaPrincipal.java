package principal;

import controlador.LoginControlador;
import modelo.CarDealership;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		CarDealership c = new CarDealership("Valencia Motors", "Valencia", 3);;
		LoginControlador cont = new LoginControlador();
		cont.openWindow(c);
	
	}
}
