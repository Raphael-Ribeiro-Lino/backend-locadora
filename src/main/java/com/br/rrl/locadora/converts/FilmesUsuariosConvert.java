package com.br.rrl.locadora.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmesUsuariosConvert {

	@Autowired
	private ModelMapper modelMapper;
}
