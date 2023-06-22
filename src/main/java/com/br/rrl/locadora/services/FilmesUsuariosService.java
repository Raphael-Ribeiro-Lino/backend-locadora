package com.br.rrl.locadora.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.entities.FilmeEntity;
import com.br.rrl.locadora.entities.FilmesUsuariosEntity;
import com.br.rrl.locadora.exceptions.BadRequestBussinessException;
import com.br.rrl.locadora.exceptions.NotFoundBussinessException;
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
		atualizaQuantidade(filmeEntity, "subtração");
		FilmesUsuariosEntity filmesUsuariosEntity = criaFilmesUsuariosEntity(filmeEntity);
		return filmesUsuariosRepository.save(filmesUsuariosEntity);
	}

	private FilmesUsuariosEntity criaFilmesUsuariosEntity(FilmeEntity filmeEntity) {
		FilmesUsuariosEntity filmesUsuariosEntity = new FilmesUsuariosEntity();
		filmesUsuariosEntity.setFilme(filmeEntity);
		filmesUsuariosEntity.setUsuario(tokenService.getUserByToken());
		filmesUsuariosEntity.setDataLocacao(LocalDateTime.now());
		filmesUsuariosEntity.setDataDevolucao(LocalDateTime.now().plusDays(7));
		return filmesUsuariosEntity;
	}

	private void atualizaQuantidade(FilmeEntity filmeEntity, String operacao) {
		int quantidade = filmeEntity.getQuantidade();
		if(operacao.equals("soma")) {			
			filmeEntity.setQuantidade(quantidade + 1);
		}
		if(operacao.equals("subtração")) {
			filmeEntity.setQuantidade(quantidade - 1);
		}
		if (quantidade == 0) {
			throw new BadRequestBussinessException(
					"Não foi possível fazer a locação do filme, pois ele já está alugado, tente novamente mais tarde!");
		}
		
		filmeService.alteraQuantidade(filmeEntity);
	}

	public FilmesUsuariosEntity buscaPorId(Long idFilmesUsuarios) {
		return filmesUsuariosRepository.findByIdAndUsuario(idFilmesUsuarios, tokenService.getUserByToken())
				.orElseThrow(() -> new NotFoundBussinessException("Filme alugado não encontrado!"));
	}

	@Transactional
	public FilmesUsuariosEntity devolucao(FilmesUsuariosEntity filmesUsuariosEncontrado) {
		filmesUsuariosEncontrado.setDevolvido(true);
		atualizaQuantidade(filmesUsuariosEncontrado.getFilme(), "soma");
		return filmesUsuariosRepository.save(filmesUsuariosEncontrado);
	}
}
