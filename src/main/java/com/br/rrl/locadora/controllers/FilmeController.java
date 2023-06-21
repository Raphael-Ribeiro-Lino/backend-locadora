package com.br.rrl.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.rrl.locadora.configs.ControllerConfig;
import com.br.rrl.locadora.configs.securities.PodeAcessarSe;
import com.br.rrl.locadora.converts.FilmeConvert;
import com.br.rrl.locadora.dto.inputs.FilmeInput;
import com.br.rrl.locadora.dto.outputs.FilmeOutput;
import com.br.rrl.locadora.entities.FilmeEntity;
import com.br.rrl.locadora.services.FilmeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/filmes")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private FilmeConvert filmeConvert;
	
	@PostMapping("/cadastra")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PodeAcessarSe.TemPerfilLocadora
	public FilmeOutput cadastra(@RequestBody @Valid FilmeInput filmeInput) {
		FilmeEntity filmeEntity = filmeConvert.inputToEntity(filmeInput);
		FilmeEntity filmeCadastrado = filmeService.cadastra(filmeEntity);
		return filmeConvert.entityToOutput(filmeCadastrado);
	}
	
	@GetMapping("/lista")
	@PodeAcessarSe.EstaAutenticado
	public Page<FilmeOutput> listaTodos(@PageableDefault(sort = "titulo", direction = Direction.ASC) Pageable paginacao){
		Page<FilmeEntity> filmes = filmeService.listaTodos(paginacao);
		return filmeConvert.pageEntityToPageOutput(filmes);
	}
	
	@GetMapping()
	public Page<FilmeOutput> buscaPorTitulo(@PageableDefault(sort = "titulo", direction = Direction.ASC) Pageable paginacao, @RequestParam String titulo){
		Page<FilmeEntity> filmes = filmeService.buscaPorTitulo(paginacao, titulo);
		return filmeConvert.pageEntityToPageOutput(filmes);
	}
}
