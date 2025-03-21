package modelo;

import java.util.Map;

public interface WorkerDAO {

	//public boolean comprobarWorker(Worker worker);
	public Map<String, Model> getModels(CarDealership cardealer);
	public Map<String, Client> getClients();
	/*public boolean insertarUsuario(Usuario usuario);
	
	public boolean eliminarUsuario(String nombre);
	public boolean modificarUsuario(Usuario usuario);*/
}
