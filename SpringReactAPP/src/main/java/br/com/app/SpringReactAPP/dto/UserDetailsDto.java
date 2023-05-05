package br.com.app.SpringReactAPP.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.app.SpringReactAPP.model.Perfil;
import br.com.app.SpringReactAPP.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsDto implements UserDetails {

	private static final long serialVersionUID = 100529676734458376L;
	private Long id;
	private String usuario;
	private String senha;
	private String nome;
	private List<Perfil> perfis;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Perfil p : this.perfis) {
			grantedAuthorities.add(new SimpleGrantedAuthority(p.getNome()));
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static UserDetailsDto fromUser(Usuario user) {
		return new UserDetailsDto(user.getId(), user.getUsername(), user.getPassword(), user.getNome(), user.getPerfis());
	}

	
}
