package model;

import java.util.Map;

public interface WorkerDAO {

	//public boolean comprobarWorker(Worker worker);
	public Map<String, Model> getModels(Worker worker);
	public Map<String, Client> getClients();
	public boolean modifyModel(Model model);
	public boolean insertClient(Client client);
	/*public boolean insertarUsuario(Usuario usuario);
	
	public boolean eliminarUsuario(String nombre);
	public boolean modificarUsuario(Usuario usuario);*/
}
