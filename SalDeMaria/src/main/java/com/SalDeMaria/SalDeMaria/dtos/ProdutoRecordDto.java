package com.SalDeMaria.SalDeMaria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRecordDto(
    @NotBlank String nome, 
    @NotNull @Positive double preco, 
    @Positive int quantidade, 
    @Size(max = 250) String descricao) {

}

