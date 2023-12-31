package com.br.rrl.locadora.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.rrl.locadora.entities.UsuarioEntity;
import com.br.rrl.locadora.exceptions.NotFoundBussinessException;
import com.br.rrl.locadora.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public UsuarioEntity cadastra(UsuarioEntity usuarioEntity) {
		String senha = usuarioEntity.getSenha();
		usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(senha));
		return usuarioRepository.save(usuarioEntity);
	}

	public UsuarioEntity buscaPorEmail(String email) {
		return usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundBussinessException("Usuário não encontrado"));
	}

	public UsuarioEntity buscaPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundBussinessException("Usuário " + id + " não encontrado"));
	}

	public List<UsuarioEntity> listaTodos() {
		return usuarioRepository.findAll();
	}
}
