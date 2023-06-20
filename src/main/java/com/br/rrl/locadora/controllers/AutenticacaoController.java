package com.br.rrl.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.rrl.locadora.configs.ControllerConfig;
import com.br.rrl.locadora.dto.inputs.LoginInput;
import com.br.rrl.locadora.dto.outputs.TokenOutput;
import com.br.rrl.locadora.exceptions.BadRequestBussinessException;
import com.br.rrl.locadora.services.TokenService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	

	@PostMapping
	public TokenOutput autenticar(@RequestBody @Valid LoginInput login) {
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		try {
			Authentication authentication = (Authentication) authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication, login);
			return new TokenOutput(token, "Bearer");
		} catch (AuthenticationException e) {
			throw new BadRequestBussinessException("E-mail ou Senha Inválida");
		}
	}
}
