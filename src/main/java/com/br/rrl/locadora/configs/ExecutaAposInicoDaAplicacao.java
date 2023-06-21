package com.br.rrl.locadora.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.br.rrl.locadora.entities.UsuarioEntity;
import com.br.rrl.locadora.enums.PerfilEnum;
import com.br.rrl.locadora.services.UsuarioService;


@Configuration
public class ExecutaAposInicoDaAplicacao implements ApplicationRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<UsuarioEntity> listaTodos = usuarioService.listaTodos();

		if (listaTodos.isEmpty()) {
			cadastraUsuarioSeNecessario("usuario.locadora@gmail.com", "Usuario Locadora", PerfilEnum.LOCADORA);
		}
	}

	private void cadastraUsuarioSeNecessario(String email, String nome, PerfilEnum perfil) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setEmail(email);
		usuarioEntity.setNomeCompleto(nome);
		usuarioEntity.setSenha("12345678");
		usuarioEntity.setPerfil(perfil);
		usuarioService.cadastra(usuarioEntity);
	}

}