package modelo;

import java.time.LocalDate;
import java.util.Map;

public interface WorkerDAO {

	//public boolean comprobarWorker(Worker worker);
	/*public boolean insertarUsuario(Usuario usuario);
	public Map<String, Usuario> visualizarUsuarios();
	public boolean eliminarUsuario(String nombre);
	public boolean modificarUsuario(Usuario usuario);*/
	
	public Map<String, Client_> getClients_();
	public Map<String, Model> getModels(CarDealership cardealer);
	public boolean callProcedure(Client_ client, Model model, CarDealership carDealer, LocalDate actualDate, int quantity);
	public boolean comprobarStock(Model model, CarDealership carDealer);
	
}
