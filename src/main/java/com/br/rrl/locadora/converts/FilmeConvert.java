package com.br.rrl.locadora.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.rrl.locadora.dto.inputs.FilmeInput;
import com.br.rrl.locadora.dto.outputs.FilmeOutput;
import com.br.rrl.locadora.entities.FilmeEntity;

import jakarta.validation.Valid;

@Component
public class FilmeConvert {

	@Autowired
	private ModelMapper modelMapper;

	public FilmeEntity inputToEntity(@Valid FilmeInput filmeInput) {
		return modelMapper.map(filmeInput, FilmeEntity.class);
	}

	public FilmeOutput entityToOutput(FilmeEntity filmeCadastrado) {
		return modelMapper.map(filmeCadastrado, FilmeOutput.class);
	}
}
