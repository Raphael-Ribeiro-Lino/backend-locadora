package com.br.rrl.locadora.dto.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeInput {

	@NotBlank(message = "O campo título é obrigatório")
	private String titulo;
	
	@NotBlank(message = "O campo diretor é obrigatório")
	private String diretor;
	
	@NotBlank(message = "O campo quantidade é obrigatório")
	private int quantidade = 1;
}
