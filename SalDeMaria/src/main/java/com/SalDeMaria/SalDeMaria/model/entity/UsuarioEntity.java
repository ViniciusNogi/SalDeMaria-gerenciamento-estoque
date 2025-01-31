package com.SalDeMaria.SalDeMaria.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Usuarios")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    private UsuarioPermissao permissao;

    public UsuarioEntity(String login, String password, UsuarioPermissao permissao){
        this.login = login;
        this.password = password;
        this.permissao = permissao;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.permissao == UsuarioPermissao.ADMIN) return List.of(new SimpleGrantedAuthority("PEFIL_ADMIN"), new SimpleGrantedAuthority("PERFIL_USUARIO"));
        else return List.of(new SimpleGrantedAuthority("PERFIL_USUARIO"));
    }

    @Override
    public String getUsername() {
        return login;
    }


    public UsuarioEntity(String login2, String encryptedPassword, String permissao) {
        //TODO Auto-generated constructor stub
    }
}
