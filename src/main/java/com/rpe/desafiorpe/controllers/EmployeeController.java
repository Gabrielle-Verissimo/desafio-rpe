package com.rpe.desafiorpe.controllers;

import com.rpe.desafiorpe.dtos.EmployeeRecordDto;
//import com.rpe.desafiorpe.exceptions.InvalidCpfException;
import com.rpe.desafiorpe.models.EmployeeModel;
import com.rpe.desafiorpe.services.EmployeeService;
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
@RequestMapping("/api/pessoa-funcionario")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @Operation(description = "Cadastra um novo funcionário.")
    @ApiResponse(responseCode = "200", description = "Funcionario cadastrado com sucesso.")
    @PostMapping("/cadastrar")
    public ResponseEntity<EmployeeModel> register(@RequestBody @Valid EmployeeRecordDto employeeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(employeeDto));
    }
    @Operation(description = "Lista todos os funcionários")
    @ApiResponse(responseCode = "200", description = "Todos os funcionarios retornados com sucesso.")
    @GetMapping("/todos")
    public ResponseEntity<List<EmployeeModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(description = "Busca o funcionário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario retornado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<EmployeeModel> employee= service.findById(id);
        if(employee.isEmpty()) {
            throw new RuntimeException("Funcionário não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee.get());
    }

    @Operation(description = "Busca o funcionário pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario retornado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado.")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable(value = "cpf") String cpf){
        Optional<EmployeeModel> employee= service.findByCpf(cpf);
        if(employee.isEmpty()) {
            throw new RuntimeException("Funcionário não encontrado. Verifique se o cpf está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee.get());
    }

    @Operation(description = "Edita o funcionário buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario editado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado.")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid EmployeeRecordDto employeeDto) {
        Optional<EmployeeModel> employee= service.findById(id);
        if(employee.isEmpty()){
            throw new RuntimeException("Funcionário não encontrado. Verifique se o id está correto.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.update(employeeDto, employee));
    }

    @Operation(description = "Exclui o funcionário buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado.")
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<EmployeeModel> employee= service.findById(id);
        if(employee.isEmpty()){
            throw new RuntimeException("Funcionário não encontrado. Verifique se o id está correto.");
        }
        service.delete(employee.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario excluído com sucesso");
    }
}
