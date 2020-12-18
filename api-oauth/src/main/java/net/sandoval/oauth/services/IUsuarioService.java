package net.sandoval.oauth.services;



import net.sandoval.commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	public Usuario update(Usuario usuario,  Long id);

}
