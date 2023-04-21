package br.com.app.SpringReactAPP.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "perfil")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = -7677228420893458501L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nivel;
	private String nome;
	
	@Override
	public String getAuthority() {
		return this.nivel;
	}

	public Perfil(String nivel, String nome) {
		super();
		this.nivel = nivel;
		this.nome = nome;
	}

}
