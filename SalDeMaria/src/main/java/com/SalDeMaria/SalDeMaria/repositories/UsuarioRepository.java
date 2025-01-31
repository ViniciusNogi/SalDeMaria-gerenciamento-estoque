package com.SalDeMaria.SalDeMaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.SalDeMaria.SalDeMaria.model.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String>{


    UserDetails findByLogin(String login);

}
