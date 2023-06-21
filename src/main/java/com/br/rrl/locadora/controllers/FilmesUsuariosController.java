package com.br.rrl.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.rrl.locadora.configs.ControllerConfig;
import com.br.rrl.locadora.configs.securities.PodeAcessarSe;
import com.br.rrl.locadora.converts.FilmesUsuariosConvert;
import com.br.rrl.locadora.dto.outputs.FilmesUsuariosOutput;
import com.br.rrl.locadora.entities.FilmesUsuariosEntity;
import com.br.rrl.locadora.services.FilmesUsuariosService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/filmes-usuarios")
public class FilmesUsuariosController {

	@Autowired
	private FilmesUsuariosService filmesUsuariosService;
	
	@Autowired
	private FilmesUsuariosConvert filmesUsuariosConvert;
	
	@PostMapping("/locacao-filme/{idFilme}")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PodeAcessarSe.EstaAutenticado
	public FilmesUsuariosOutput locacaoFilme(@PathVariable Long idFilme) {
		FilmesUsuariosEntity filmesUsuariosEntity = filmesUsuariosService.locacao(idFilme);
		return filmesUsuariosConvert.entityToOutput(filmesUsuariosEntity);
	}
}
