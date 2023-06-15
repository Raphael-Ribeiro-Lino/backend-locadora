package com.br.rrl.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.rrl.locadora.configs.ControllerConfig;
import com.br.rrl.locadora.converts.UsuarioConvert;
import com.br.rrl.locadora.dto.inputs.UsuarioInput;
import com.br.rrl.locadora.dto.outputs.UsuarioOutput;
import com.br.rrl.locadora.entities.UsuarioEntity;
import com.br.rrl.locadora.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConvert usuarioConvert;

	@PostMapping("/cadastra")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioOutput cadasta(@RequestBody @Valid UsuarioInput usuarioInput) {
		UsuarioEntity usuarioEntity = usuarioConvert.inputToEntity(usuarioInput);
		UsuarioEntity usuarioCadastrado = usuarioService.cadastra(usuarioEntity);
		return usuarioConvert.entityToOutput(usuarioCadastrado);
	}
}
