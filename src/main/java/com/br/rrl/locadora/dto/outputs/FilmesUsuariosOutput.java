package com.br.rrl.locadora.dto.outputs;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmesUsuariosOutput {

	private Long id;
	
	private FilmeOutput filme;
	
	private UsuarioOutput usuario;
	
	private LocalDateTime dataLocacao;
	
	private LocalDateTime dataDevolucao;
	
}
