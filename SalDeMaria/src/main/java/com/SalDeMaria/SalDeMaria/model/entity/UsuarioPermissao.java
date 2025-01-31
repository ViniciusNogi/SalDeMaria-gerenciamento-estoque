package com.SalDeMaria.SalDeMaria.model.entity;

public enum UsuarioPermissao {
    ADMIN("admin"),
    USUARIO("usuario");

    private String permissao;

    UsuarioPermissao(String permissao){
        this.permissao = permissao;
    }

    public String getRole(){
        return permissao;
    }
}
