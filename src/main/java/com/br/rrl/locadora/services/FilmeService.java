package com.br.rrl.locadora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.entities.FilmeEntity;
import com.br.rrl.locadora.repositories.FilmeRepository;

import jakarta.transaction.Transactional;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private TokenService tokenService;

	@Transactional
	public FilmeEntity cadastra(FilmeEntity filmeEntity) {
		filmeEntity.setUsuario(tokenService.getUserByToken());
		return filmeRepository.save(filmeEntity);
	}
}
