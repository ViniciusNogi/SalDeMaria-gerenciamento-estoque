package com.SalDeMaria.SalDeMaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SalDeMaria.SalDeMaria.dtos.AuthenticationDTO;
import com.SalDeMaria.SalDeMaria.dtos.LoginResponseDTO;
import com.SalDeMaria.SalDeMaria.dtos.RegisterDTO;
import com.SalDeMaria.SalDeMaria.infra.security.TokenService;
import com.SalDeMaria.SalDeMaria.model.entity.UsuarioEntity;
import com.SalDeMaria.SalDeMaria.repositories.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@Tag(name = "usuário", description = "Controlador para salvar e editar dados de usuarios")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Autentica um usuário e retorna um token JWT.")
    @ApiResponse(responseCode = "201", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor.")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.geradorToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro", description = "Registra um novo usuário no sistema.")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<?> registro(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioEntity novoUsuario = new UsuarioEntity(data.login(), encryptedPassword, data.permissao());
        this.repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
