package br.com.app.SpringReactAPP.model;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
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
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Menu.class, cascade = CascadeType.ALL)
	@OrderBy("id asc")
	@JoinTable(name = "perfil_menu", joinColumns = { @JoinColumn(name = "perfil_id") }, inverseJoinColumns = {
			@JoinColumn(name = "menu_id") })
	private Set<Menu> menus;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = SubMenu.class)
	@OrderBy("id asc")
	@JoinTable(name = "perfil_submenu", joinColumns = { @JoinColumn(name = "perfil_id") }, inverseJoinColumns = {
			@JoinColumn(name = "submenu_id") })
	private Set<SubMenu> submenus;
	
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
