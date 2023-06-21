package com.br.rrl.locadora.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_filmes_usuarios")
@Getter
@Setter
public class FilmesUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "usuario")
	@ManyToOne
	private UsuarioEntity usuario;
	
	@JoinColumn(name = "filme")
	@ManyToOne
	private FilmeEntity filme;
	
	@Column(name = "data_locacao")
	private LocalDateTime dataLocacao;
	
	@Column(name = "data_devolucao")
	private LocalDateTime dataDevolucao;
}
