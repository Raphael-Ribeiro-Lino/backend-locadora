package com.br.rrl.locadora.services;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.dto.inputs.LoginInput;
import com.br.rrl.locadora.entities.UsuarioEntity;
import com.br.rrl.locadora.exceptions.UnauthorizedAccessBussinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

	@Autowired
	private HttpServletRequest resquest;

	@Autowired
	private UsuarioService usuarioService;
	
	public String gerarToken(Authentication authentication, LoginInput login) {
		UsuarioEntity logado = usuarioService.buscaPorEmail(authentication.getName());
		Date dataExpiracao = new Date();
		
		JwtBuilder builder = Jwts.builder();
		builder.claim("id", logado.getId());
		builder.claim("email", logado.getEmail());
		builder.claim("nomeCompleto", logado.getNomeCompleto());
		builder.claim("perfil", logado.getPerfil());
		builder.setIssuer("API Locadora");
		builder.setIssuedAt(dataExpiracao);
		builder.setExpiration(new Date(dataExpiracao.getTime() + Long.parseLong("86400000")));
		builder.signWith(getSignInKey(), SignatureAlgorithm.HS256);
		return builder.compact();
	}

	public UsuarioEntity getUserByToken() {

		Claims claims = extractClaims();
		Long id = Long.valueOf(claims.get("id").toString());
		return usuarioService.buscaPorId(id);

	}

	public boolean canAccessAuthenticated() {
		Claims claims = extractClaims();
		if (claims != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canAccessByPerfil(List<String> perfis) {
		UsuarioEntity usuario = getUserByToken();
		for (String perfil : perfis) {
			if (perfil.toUpperCase().equals(usuario.getPerfil().toString())) {
				return true;
			}
		}
		return false;
	}

	private Claims extractClaims() {
		String token = resquest.getHeader("Authorization");
		token = token.substring(7);
		try {
			return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new UnauthorizedAccessBussinessException("Token inválido!");
		}
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode("2D4A614E645267556B58703273357638792F423F4528482B4D6250655368566D");
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
