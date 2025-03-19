package modelo;

import java.util.Map;

public interface UsuarioDAO {

	public boolean comprobarWorker(Worker worker);
	public Map<String, Model> visualizeModels();
	/*public boolean insertarUsuario(Usuario usuario);
	
	public boolean eliminarUsuario(String nombre);
	public boolean modificarUsuario(Usuario usuario);*/
}
