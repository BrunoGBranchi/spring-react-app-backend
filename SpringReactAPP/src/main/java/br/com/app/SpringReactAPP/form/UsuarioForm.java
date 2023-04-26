package br.com.app.SpringReactAPP.form;

import br.com.app.SpringReactAPP.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UsuarioForm {
	
	private Usuario usuario;

	private String confirmaSenha;

	public UsuarioForm() {
		super();
		this.usuario = new Usuario();
	}

	public static UsuarioForm from(Usuario usuario) {
		return new UsuarioForm(usuario, null);
	}

}
