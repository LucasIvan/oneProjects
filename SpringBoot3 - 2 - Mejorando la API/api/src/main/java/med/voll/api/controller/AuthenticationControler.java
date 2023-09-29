package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.DTO.DatosAuthenticationUser;
import med.voll.api.domain.DTO.DatosJwtToken;
import med.voll.api.domain.model.Usuario;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationControler {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> userAuth(@RequestBody @Valid DatosAuthenticationUser datosAuthenticationUser){
		Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuthenticationUser.login(), 
				datosAuthenticationUser.password());
		var userAuth = authenticationManager.authenticate(authToken);
		var JwtToken = tokenService.generarToken((Usuario) userAuth.getPrincipal());
		return ResponseEntity.ok(new DatosJwtToken(JwtToken));
	}
}
