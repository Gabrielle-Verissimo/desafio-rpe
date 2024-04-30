package com.rpe.desafiorpe.controllers;

import com.rpe.desafiorpe.dtos.PessoaClienteRecordDto;
import com.rpe.desafiorpe.models.PessoaClienteModel;
import com.rpe.desafiorpe.models.PessoaFuncionarioModel;
import com.rpe.desafiorpe.services.PessoaClienteService;
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
public class PessoaClienteController {
    @Autowired
    PessoaClienteService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaClienteModel> register(@RequestBody @Valid PessoaClienteRecordDto clienteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PessoaClienteModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<PessoaClienteModel> cliente = service.findById(id);
        if(cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable(value = "cpf") String cpf){
        Optional<PessoaClienteModel> cliente = service.findByCpf(cpf);
        if(cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado. Verifique se o cpf está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PessoaClienteRecordDto clienteDto) {
        Optional<PessoaClienteModel> cliente = service.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.update(clienteDto, cliente));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<PessoaClienteModel> cliente = service.findById(id);
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado. Verifique se o id está correto.");
        }
        service.delete(cliente.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso");
    }
}
