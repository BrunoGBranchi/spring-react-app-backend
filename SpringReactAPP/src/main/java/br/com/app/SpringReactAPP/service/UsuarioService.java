package br.com.app.SpringReactAPP.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.app.SpringReactAPP.form.UsuarioForm;
import br.com.app.SpringReactAPP.model.Perfil;
import br.com.app.SpringReactAPP.model.Usuario;
import br.com.app.SpringReactAPP.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	public Usuario findById(Long id) {
		Optional<Usuario> findById = usuarioRepository.findById(id);
		return findById.orElse(null);
	}
	
	public Usuario salvar(UsuarioForm usuarioForm) {

		Usuario usuarioTela = usuarioForm.getUsuario();
				
		if (StringUtils.isBlank(usuarioTela.getSenha())) {
			Optional<Usuario> findById = usuarioRepository.findById(usuarioTela.getId());
			if (findById.isPresent()) {
				usuarioTela.setSenha(findById.get().getSenha());
				for (Perfil iterable_element : usuarioForm.getUsuario().getPerfis()) {
					System.out.println(iterable_element.getNome());
				}
				usuarioTela.setPerfis(usuarioForm.getUsuario().getPerfis());
			}
		}else {
			
			usuarioTela.setSenha(passwordEncoder.encode(usuarioForm.getUsuario().getSenha()));
		}
		
		return usuarioRepository.save(usuarioTela);
	}
	
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
