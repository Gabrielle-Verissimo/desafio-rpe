package com.rpe.desafiorpe.controllers;

import com.rpe.desafiorpe.dtos.PessoaFuncionarioRecordDto;
import com.rpe.desafiorpe.models.PessoaFuncionarioModel;
import com.rpe.desafiorpe.services.PessoaFuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoa-funcionario")
public class PessoaFuncionarioController {
    @Autowired
    PessoaFuncionarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<PessoaFuncionarioModel> register(@RequestBody @Valid PessoaFuncionarioRecordDto funcionarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(funcionarioDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PessoaFuncionarioModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<PessoaFuncionarioModel> funcionario = service.findById(id);
        if(funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable(value = "cpf") String cpf){
        Optional<PessoaFuncionarioModel> funcionario = service.findByCpf(cpf);
        if(funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado. Verifique se o cpf está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PessoaFuncionarioRecordDto funcionarioDto) {
        Optional<PessoaFuncionarioModel> funcionario = service.findById(id);
        if(funcionario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.update(funcionarioDto, funcionario));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<PessoaFuncionarioModel> funcionario = service.findById(id);
        if(funcionario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado. Verifique se o id está correto.");
        }
        service.delete(funcionario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario excluído com sucesso");
    }
}
