package com.br.rrl.locadora.dto.inputs;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInput {

	@NotBlank(message = "Preenchimento do e-mail é obrigatório")
	private String email;
	
	@NotBlank(message = "Preenchimento da senha é obrigatório")
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
