package com.br.rrl.locadora.dto.inputs;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeInput {

	@NotBlank(message = "O campo título é obrigatório")
	private String titulo;
	
	@NotBlank(message = "O campo diretor é obrigatório")
	private String diretor;
	
	@NotNull(message = "O campo quantidade é obrigatório")
	private int quantidade;
	
	@AssertTrue(message = "O campo quantidade está inválido")
	public boolean isNomeQuantidadeValida() {
		if(quantidade == 0) {
			return false;
		}
		
		return true;
	}
}
