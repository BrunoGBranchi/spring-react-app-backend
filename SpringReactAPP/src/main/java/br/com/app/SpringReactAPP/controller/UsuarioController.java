package br.com.app.SpringReactAPP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.SpringReactAPP.model.Usuario;
import br.com.app.SpringReactAPP.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @GetMapping
    public List<Usuario> getClients() {
        return usuarioRepository.findAll();
    }
	
}
