package com.br.rrl.locadora.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmeConvert {

	@Autowired
	private ModelMapper modelMapper;
}
