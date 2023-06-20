package com.br.rrl.locadora.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PerfilEnum implements GrantedAuthority{
	CLIENTE, LOCADORA;
	@Override
	public String getAuthority() {
		return name();
	}

}
