package com.br.rrl.locadora.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.rrl.locadora.dto.inputs.UsuarioInput;
import com.br.rrl.locadora.dto.outputs.UsuarioOutput;
import com.br.rrl.locadora.entities.UsuarioEntity;

@Component
public class UsuarioConvert {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioEntity inputToEntity(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, UsuarioEntity.class);
	}

	public UsuarioOutput entityToOutput(UsuarioEntity usuarioCadastrado) {
		return modelMapper.map(usuarioCadastrado, UsuarioOutput.class);
	}
}
