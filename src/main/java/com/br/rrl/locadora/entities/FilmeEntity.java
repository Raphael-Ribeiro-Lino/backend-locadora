package com.br.rrl.locadora.entities;

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
@Table(name = "tb_filmes")
@Getter
@Setter
public class FilmeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "diretor")
	private String diretor;
	
	@Column(name = "quantidade")
	private int quantidade = 1;
	
	@JoinColumn(name = "locadora")
	@ManyToOne
	private UsuarioEntity usuario;
}
