package com.br.rrl.locadora.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.rrl.locadora.entities.FilmeEntity;

public interface FilmeRepository extends JpaRepository<FilmeEntity, Long>{

	Page<FilmeEntity> findAllByTitulo(String titulo, Pageable paginacao);

	Page<FilmeEntity> findByTituloContains(String titulo, Pageable paginacao);

}
