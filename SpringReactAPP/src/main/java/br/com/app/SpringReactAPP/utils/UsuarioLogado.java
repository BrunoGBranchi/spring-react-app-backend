package br.com.app.SpringReactAPP.utils;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.app.SpringReactAPP.dto.UserDetailsDto;
import br.com.app.SpringReactAPP.model.Usuario;
import br.com.app.SpringReactAPP.repository.UsuarioRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioLogado {

	public static Usuario getUserDetailsDto() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			return (Usuario) authentication.getPrincipal();
		}
		return null;
	}

	public static Usuario getUsuarioLogado() {
		return getUserDetailsDto();
	}
	
	public static Optional<Usuario> getUsuarioLogadoCompleto(UsuarioRepository userRepository) {
		if (userRepository != null) {
			return userRepository.findById(getUserDetailsDto().getId());
		}
		return Optional.empty();
	}
	
}
