package com.SalDeMaria.SalDeMaria.dtos;

import com.SalDeMaria.SalDeMaria.model.entity.UsuarioPermissao;

public record RegisterDTO(String login, String password, UsuarioPermissao permissao) {

}
