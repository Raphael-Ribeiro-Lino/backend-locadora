package com.br.rrl.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.rrl.locadora.configs.ControllerConfig;
import com.br.rrl.locadora.converts.UsuarioConvert;
import com.br.rrl.locadora.services.UsuarioService;


@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioConvert usuarioConvert;
	
}
