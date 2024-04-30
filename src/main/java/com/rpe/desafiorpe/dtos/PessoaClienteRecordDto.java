package com.rpe.desafiorpe.dtos;
import jakarta.validation.constraints.NotBlank;
public record PessoaClienteRecordDto(@NotBlank String cpf, @NotBlank String nome, @NotBlank String endereco, @NotBlank String telefone, @NotBlank String dataDoUltimoServico) {
}
