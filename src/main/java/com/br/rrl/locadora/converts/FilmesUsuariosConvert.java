package com.br.rrl.locadora.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.rrl.locadora.dto.outputs.FilmesUsuariosOutput;
import com.br.rrl.locadora.entities.FilmesUsuariosEntity;

@Component
public class FilmesUsuariosConvert {

	@Autowired
	private ModelMapper modelMapper;

	public FilmesUsuariosOutput entityToOutput(FilmesUsuariosEntity filmesUsuariosEntity) {
		return modelMapper.map(filmesUsuariosEntity, FilmesUsuariosOutput.class);
	}
}
