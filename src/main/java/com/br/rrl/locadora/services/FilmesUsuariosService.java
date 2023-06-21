package com.br.rrl.locadora.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.entities.FilmeEntity;
import com.br.rrl.locadora.entities.FilmesUsuariosEntity;
import com.br.rrl.locadora.exceptions.BadRequestBussinessException;
import com.br.rrl.locadora.repositories.FilmesUsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class FilmesUsuariosService {

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private FilmesUsuariosRepository filmesUsuariosRepository;

	@Autowired
	private TokenService tokenService;

	@Transactional
	public FilmesUsuariosEntity locacao(Long idFilme) {
		FilmeEntity filmeEntity = filmeService.buscaPorId(idFilme);
		atualizaQuantidade(filmeEntity);
		FilmesUsuariosEntity filmesUsuariosEntity = new FilmesUsuariosEntity();
		filmesUsuariosEntity.setFilme(filmeEntity);
		filmesUsuariosEntity.setUsuario(tokenService.getUserByToken());
		filmesUsuariosEntity.setDataLocacao(LocalDateTime.now());
		filmesUsuariosEntity.setDataDevolucao(LocalDateTime.now().plusDays(7));
		filmeService.alteraQuantidade(filmeEntity);
		return filmesUsuariosRepository.save(filmesUsuariosEntity);
	}

	private void atualizaQuantidade(FilmeEntity filmeEntity) {
		int quantidade = filmeEntity.getQuantidade();
		filmeEntity.setQuantidade(quantidade - 1);
		if (quantidade == 0) {
			throw new BadRequestBussinessException(
					"Não foi possível fazer a locação do filme, pois ele já está alugado, tente novamente mais tarde!");
		}
	}
}
