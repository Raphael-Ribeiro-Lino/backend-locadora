package com.br.rrl.locadora.dto.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutput {

	private Long id;
	
	private String email;
	
	private String nomeCompleto;
}
