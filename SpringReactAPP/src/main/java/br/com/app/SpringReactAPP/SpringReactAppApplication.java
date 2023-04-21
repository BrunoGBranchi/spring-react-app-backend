package br.com.app.SpringReactAPP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.app.SpringReactAPP.model.Perfil;
import br.com.app.SpringReactAPP.model.Usuario;
import br.com.app.SpringReactAPP.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringReactAppApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringReactAppApplication.class, args);
	}
	
	@PostConstruct
	protected void init() {
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(createPerfil("USER", "Padrão"));
		perfis.add(createPerfil("ADMIN", "Administrador"));
		
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setNome("Administrador");
		usuario.setSenha(passwordEncoder.encode("admin"));
		usuario.setAtivo(true);
		usuario.setPerfis(perfis);
		usuarioRepository.save(usuario);
		
		
	}
	
	private Perfil createPerfil(String nivel, String nome) {
		Perfil perfil = new Perfil();
		perfil.setNivel("USER");
		perfil.setNome("Padrão");
		return perfil;
	}
	

}
