package com.br.rrl.locadora.dto.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeOutput {

	private Long id;
	
	private String titulo;
	
	private String diretor;
	
	private int quantidade;
}
