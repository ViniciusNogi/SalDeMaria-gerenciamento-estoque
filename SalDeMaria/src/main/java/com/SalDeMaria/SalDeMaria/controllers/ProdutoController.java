package com.SalDeMaria.SalDeMaria.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SalDeMaria.SalDeMaria.dtos.ProdutoRecordDto;
import com.SalDeMaria.SalDeMaria.model.entity.ProdutoEntity;
import com.SalDeMaria.SalDeMaria.repositories.ProdutoRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Operation(summary = "Cadastrar", description = "Método para cadastrar produtos", tags = "produtos")
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoEntity> cadastrarProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        var produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoRecordDto, produtoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoEntity));
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoEntity>> buscarTudo() {
        List<ProdutoEntity> produtoLista = produtoRepository.findAll();
        if (!produtoLista.isEmpty()) {
            for (ProdutoEntity produto : produtoLista) {
                UUID id = produto.getId();
                produto.add(linkTo(methodOn(ProdutoController.class).buscarUm(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produto.get().add(linkTo(methodOn(ProdutoController.class).buscarTudo()).withRel("Lista de produtos"));
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value="id") UUID id){
        Optional<ProdutoEntity>produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoRepository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido com sucesso!");
    }
    
    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable (value = "id") UUID id, @RequestBody @Valid ProdutoRecordDto produtoRecordDto){
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        var produtoEntity = produto.get();
        BeanUtils.copyProperties(produtoRecordDto, produtoEntity);
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoEntity));
    } 
        
}
