package com.br.rrl.locadora.dto.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank(message = "O campo e-mail é obrigatório")
	private String email;
	
	@NotBlank(message = "O campo nomeCompleto é obrigatório")
	private String nomeCompleto;
	
	@NotBlank(message = "O campo senha é obrigatório")
	private String senha;
}
