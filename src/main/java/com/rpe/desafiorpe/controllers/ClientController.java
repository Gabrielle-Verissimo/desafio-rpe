package com.rpe.desafiorpe.controllers;

import com.rpe.desafiorpe.dtos.ClientRecordDto;
import com.rpe.desafiorpe.models.ClientModel;
import com.rpe.desafiorpe.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoa-cliente")
public class ClientController {
    @Autowired
    ClientService service;

    @Operation(description = "Cadastra um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<ClientModel> register(@RequestBody @Valid ClientRecordDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clientDto));
    }

    @Operation(description = "Lista todos os clientes")
    @ApiResponse(responseCode = "200", description = "Todos os clientes retornados com sucesso.")
    @GetMapping("/todos")
    public ResponseEntity<List<ClientModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(description = "Busca o cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> client = service.findById(id);
        if(client.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client.get());
    }

    @Operation(description = "Busca o cliente pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable(value = "cpf") String cpf){
        Optional<ClientModel> client = service.findByCpf(cpf);
        if(client.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado. Verifique se o cpf está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client.get());
    }
    
    @Operation(description = "Edita o cliente buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente editado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientRecordDto clientDto) {
        Optional<ClientModel> client = service.findById(id);
        if(client.isEmpty()){
            throw new RuntimeException("Cliente não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.update(clientDto, client));
    }

    @Operation(description = "Exclui o cliente buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<ClientModel> client = service.findById(id);
        if(client.isEmpty()){
            throw new RuntimeException("Cliente não encontrado. Verifique se o id está correto.");
        }
        service.delete(client.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso");
    }
}
