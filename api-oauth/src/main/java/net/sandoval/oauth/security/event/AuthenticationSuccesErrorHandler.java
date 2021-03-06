package net.sandoval.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;
import net.sandoval.commons.usuarios.models.entity.Usuario;
import net.sandoval.oauth.services.IUsuarioService;

@Component
public class AuthenticationSuccesErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUsuarioService usuarioService;

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccesErrorHandler.class);

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {

		if (authentication.getName().equalsIgnoreCase("frontendapp")) {
			return; // si es igual a frontendapp se salen del método!
		}
		
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Succes Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);

		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el login:" + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);
		try {
			
			StringBuilder errors= new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intentos despues es de :" + usuario.getIntentos());
			
			errors.append("Intentos despues es de :" + usuario.getIntentos());
			
			
			if (usuario.getIntentos() >= 3) {
				
				
				log.error(String.format("el usuario %s des-habilitado por maximos intentos", usuario.getUsername()));

				usuario.setEnabled(false);
			}

			usuarioService.update(usuario, usuario.getId());
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}

}
