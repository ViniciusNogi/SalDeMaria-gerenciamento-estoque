package com.SalDeMaria.SalDeMaria.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SalDeMaria.SalDeMaria.model.entity.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {

}
