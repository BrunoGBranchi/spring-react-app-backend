package br.com.app.SpringReactAPP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.SpringReactAPP.JWT.JwtTokenProvider;
import br.com.app.SpringReactAPP.model.AuthRequest;
import br.com.app.SpringReactAPP.model.AuthResponse;
import br.com.app.SpringReactAPP.model.Response;
import br.com.app.SpringReactAPP.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		String token;
		try {
			token = authService.authenticate(request.getUsername(), request.getPassword());
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
		}
		
	}

	@GetMapping("/validateToken")
	private BodyBuilder validateToken(String token) {
		if (jwtTokenProvider.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("/")
	public String index() {
		return "forward:/index.html";
	}
}
