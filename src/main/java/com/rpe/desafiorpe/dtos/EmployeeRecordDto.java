package com.rpe.desafiorpe.dtos;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmployeeRecordDto(@NotBlank(message = "Este campo não pode estar em branco") String cpf, @NotBlank(message = "Este campo não pode estar em branco") String nome, @NotBlank(message = "Este campo não pode estar em branco") String endereco, @NotBlank(message = "Este campo não pode estar em branco") String telefone,
                                @NotBlank(message = "Este campo não pode estar em branco") String funcao, @NotBlank(message = "Este campo não pode estar em branco") String status, @NotBlank(message = "Este campo não pode estar em branco") String dataDeContratacao) {
}