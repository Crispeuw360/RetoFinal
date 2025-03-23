package controlador;

import java.util.Map;
import modelo.*;
import vista.VentanaPrincipal;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {

		Worker worker = new Worker(true, "Alex", "1234", 2);

		VentanaPrincipal ven = new VentanaPrincipal(this, worker);
		ven.setVisible(true);
	}

	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
	}

	public CarDealership getWorkingPlace(Worker worker) {
		return dao.getWorkingPlace(worker);
	}

	public boolean deleteModel(Model model) {
		return dao.deleteModel(model);
	}

}
