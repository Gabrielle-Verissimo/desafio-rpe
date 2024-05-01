package com.rpe.desafiorpe.dtos;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ClientRecordDto(@NotBlank(message = "Este campo não pode estar em branco") String cpf, @NotBlank(message = "Este campo não pode estar em branco") String nome, @NotBlank(message = "Este campo não pode estar em branco") String endereco,
                              @NotBlank(message = "Este campo não pode estar em branco") String telefone, @NotBlank(message = "Este campo não pode estar em branco") String dataDoUltimoServico) {
}
