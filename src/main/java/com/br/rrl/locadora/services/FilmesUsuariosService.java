package com.br.rrl.locadora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.repositories.FilmesUsuariosRepository;

@Service
public class FilmesUsuariosService {

	@Autowired
	private FilmesUsuariosRepository filmesUsuariosRepository;
}
