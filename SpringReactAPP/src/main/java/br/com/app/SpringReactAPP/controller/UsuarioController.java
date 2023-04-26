package br.com.app.SpringReactAPP.controller;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.SpringReactAPP.form.UsuarioForm;
import br.com.app.SpringReactAPP.model.Usuario;
import br.com.app.SpringReactAPP.repository.UsuarioRepository;
import br.com.app.SpringReactAPP.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
    @GetMapping
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @PostMapping("/novo")
    public ResponseEntity<Object> criarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioCriado = usuarioService.salvar(UsuarioForm.from(usuario));
            HashMap<String, String> mensagem = new HashMap<>();
            mensagem.put("message", "Usuário criado com sucesso!");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Erro na requisição, verifique os dados enviados");
        }
    }
	
}
