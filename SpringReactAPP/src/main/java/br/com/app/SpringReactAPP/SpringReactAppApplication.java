package br.com.app.SpringReactAPP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.app.SpringReactAPP.model.Menu;
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
		Menu menu = new Menu("Usuarios", "/usuarios");
		Set<Menu> menus = new HashSet<Menu>();
		menus.add(menu);
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(createPerfil("ADMIN", "Administrador", menus));
		
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setNome("Administrador");
		usuario.setSenha(passwordEncoder.encode("admin"));
		usuario.setAtivo(true);
		usuario.setPerfis(perfis);
		usuarioRepository.save(usuario);
		
		
	}
	
	private Perfil createPerfil(String nivel, String nome, Set<Menu> menus) {
		Perfil perfil = new Perfil();
		perfil.setNivel("USER");
		perfil.setNome("Padrão");
		perfil.setMenus(menus);
		return perfil;
	}
	

}
