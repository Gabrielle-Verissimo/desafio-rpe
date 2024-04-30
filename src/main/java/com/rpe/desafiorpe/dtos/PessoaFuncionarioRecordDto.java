package com.rpe.desafiorpe.dtos;
import jakarta.validation.constraints.NotBlank;

public record PessoaFuncionarioRecordDto(@NotBlank String cpf, @NotBlank String nome, @NotBlank String endereco, @NotBlank String telefone, @NotBlank String funcao, @NotBlank String status, @NotBlank String dataDeContratacao) {
}