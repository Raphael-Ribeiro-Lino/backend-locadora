package com.br.rrl.locadora.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.rrl.locadora.entities.FilmesUsuariosEntity;
import com.br.rrl.locadora.entities.UsuarioEntity;

public interface FilmesUsuariosRepository extends JpaRepository<FilmesUsuariosEntity, Long>{

	Optional<FilmesUsuariosEntity> findByIdAndUsuario(Long idFilmesUsuarios, UsuarioEntity usuario);

}
