package modelo;

import java.util.Map;

public interface UsuarioDAO {

	public boolean comprobarWorker(Worker worker);
	public Map<String, Model> visualizeModels();
	public Map<String, Worker> visualizeWorkers();
	/*public boolean insertarUsuario(Usuario usuario);
	public Map<String, Usuario> visualizarUsuarios();
	public boolean eliminarUsuario(String nombre);
	public boolean modificarUsuario(Usuario usuario);*/
}
