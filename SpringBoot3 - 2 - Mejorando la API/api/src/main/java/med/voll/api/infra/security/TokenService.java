package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.model.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;
	
	public String generarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("voll med")
		        .withSubject(usuario.getLogin())
		        .withClaim("id", usuario.getId())
		        .withExpiresAt(timer())//2hs para que expire el token
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException();
		}
	}
	
	private Instant timer() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));//del tiempo en que se crea se le suma 2hs con horario de argentina
	}
}
